package com.ellington.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ellington.home.data.Albums
import com.ellington.home.data.source.AlbumsRepository
import com.ellington.mvvm.viewmodel.BaseViewModel

class PictureListViewModel(val repository: AlbumsRepository) :
    BaseViewModel<PictureListViewModelProvider>() {

    private val _albumList = MutableLiveData<List<Albums>>().apply { value = emptyList() }
    private val _albumsResponse = MutableLiveData<Albums>().apply { value = Albums() }
    private val _loading = MutableLiveData<Boolean>()

    val albumList: LiveData<List<Albums>> = _albumList
    val isLoading: LiveData<Boolean> = _loading

    init {
        loadAlbums(true)
    }

    fun loadAlbums(forcedUpdate: Boolean = false) {

    }

    fun loadNextPageOfAlbums() {
    }

    override fun onCleared() {
    }
}