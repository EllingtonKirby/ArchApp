package com.ellington.home.viewmodel

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

class AlbumListViewModel(val repository: AlbumsRepository) :
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
            val albumsResult = repository.getAlbums(forcedUpdate)

            if (albumsResult is Result.Success) {
                val albums = albumsResult.data

                setAlbumsData(albums)
            } else {
                setAlbumsData(Albums())

                _errorMessage.value = Event(R.string.error_loading_albums)
            }

            _loading.value = false
        }
    }

    fun loadNextPageOfAlbums() {
    }

    fun getAlbumAtPosition(position: Int): Album {
        return _albumList.value?.get(position) ?: Album()
    }

    fun getAlbumListSize(): Int {
        return _albumList.value?.size ?: 0
    }

    private fun setAlbumsData(albums: Albums) {
        _albumsResponse.postValue(albums)
        _albumList.postValue(albumList.value?.plus(albums.data)?.toMutableList())
    }

    override fun onCleared() {
    }
}