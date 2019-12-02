package com.ellington.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ellington.home.R
import com.ellington.home.data.Album
import com.ellington.home.data.Albums
import com.ellington.home.data.source.AlbumsRepository
import com.ellington.mvvm.repository.Result
import com.ellington.mvvm.utils.Event
import com.ellington.mvvm.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class AlbumListViewModel(private val repository: AlbumsRepository) :
    BaseViewModel<AlbumListViewModelProvider>() {

    private val _loading = MutableLiveData<Boolean>()
    private val _albumList = MutableLiveData<MutableList<Album>>().apply { value = mutableListOf() }
    private val _albumsResponse = MutableLiveData<Albums>().apply { value = Albums() }
    private val _openAlbumEvent = MutableLiveData<Event<String>>()
    private val _errorMessage = MutableLiveData<Event<Int>>()

    val isLoading: LiveData<Boolean> = _loading
    val albumList: LiveData<MutableList<Album>> = _albumList
    val openAlbum: LiveData<Event<String>> = _openAlbumEvent
    val errorMessage: LiveData<Event<Int>> = _errorMessage

    init {
        loadAlbums(true)
    }

    fun loadAlbums(forcedUpdate: Boolean = false) {
        _loading.value = true

        viewModelScope.launch {
            val albumsResult = repository.getAlbums("2529", forcedUpdate)

            if (albumsResult is Result.Success) {
                val albums = albumsResult.data

                Log.v("Success", "Successfully fetched albums data")
                setAlbumsData(albums)
            } else {
                setAlbumsData(Albums())
                Log.e("Error", "Error fetching albums")
                _errorMessage.value = Event(R.string.error_loading_albums)
            }

            _loading.value = false
        }
    }

    fun loadNextPageOfAlbums() {
        val url = _albumsResponse.value?.next
        if (url?.isNotEmpty() == true) {
            _loading.value = true
            viewModelScope.launch {
                val albumsResult = repository.getNextPageOfAlbums(url)

                if (albumsResult is Result.Success) {
                    val albums = albumsResult.data

                    setAlbumsData(albums)
                } else {
                    Log.e("Error", "Error fetching albums")
                    _errorMessage.value = Event(R.string.error_loading_albums)
                }
                _loading.value = false
            }
        }
    }

    fun getAlbumAtPosition(position: Int): Album {
        return _albumList.value?.get(position) ?: Album()
    }

    fun getAlbumListSize(): Int {
        return _albumList.value?.size ?: 0
    }

    private fun setAlbumsData(albums: Albums) {
        _albumsResponse.postValue(albums)
        _albumList.value = albumList.value?.plus(albums.data)?.toMutableList()
    }

    override fun onCleared() {
    }
}