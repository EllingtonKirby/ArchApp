package com.ellington.home.viewmodel.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ellington.dagger.utils.PerApplication
import com.ellington.home.R
import com.ellington.home.data.Album
import com.ellington.home.data.TrackList
import com.ellington.home.data.source.AlbumsRepository
import com.ellington.mvvm.repository.Result
import com.ellington.mvvm.utils.Event
import com.ellington.mvvm.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

@PerApplication
class AlbumDetailsViewModel(private val repository: AlbumsRepository) :
    BaseViewModel<AlbumDetailsViewModelProvider>() {

    private val _loading = MutableLiveData<Boolean>()
    private val _singleAlbumData = MutableLiveData<Album>().apply { value = Album() }
    private val _trackList = MutableLiveData<TrackList>()
    private val _errorMessage = MutableLiveData<Event<Int>>()

    val isLoading: LiveData<Boolean> = _loading
    val singleAlbum: LiveData<Album> = _singleAlbumData
    val trackList: LiveData<TrackList> = _trackList
    val errorMessage: LiveData<Event<Int>> = _errorMessage

    fun loadAlbumById(albumId: String?) {
        if (albumId != null) {
            _loading.value = true
            viewModelScope.launch {
                val albumResult = repository.getAlbumById(albumId)
                if (albumResult is Result.Success) {
                    _singleAlbumData.postValue(albumResult.data)
                    loadTrackList(albumResult.data.tracklist)
                } else {
                    Log.e("Error", "Error fetching albums")
                    _errorMessage.value = Event(R.string.error_loading_album)
                }

                _loading.value = false
            }
        }
    }

    private fun loadTrackList(url: String) {
        _loading.value = true
        viewModelScope.launch {
            val trackListResult = repository.getTrackList(url)
            if (trackListResult is Result.Success) {
                _trackList.postValue(trackListResult.data)
            }
            _loading.value = false
        }
    }

    override fun onCleared() {
    }
}