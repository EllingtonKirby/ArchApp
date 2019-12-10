package com.ellington.photos.viewmodel

import com.ellington.mvvm.viewmodel.BaseViewModel
import com.ellington.photos.data.source.PhotoReelRepository

class PhotoReelViewModel(val repository: PhotoReelRepository) :
    BaseViewModel<PhotoReelViewModelProvider>() {

    override fun onCleared() {
    }
}