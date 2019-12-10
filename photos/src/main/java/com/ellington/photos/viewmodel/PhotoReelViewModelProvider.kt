package com.ellington.photos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ellington.photos.data.source.PhotoReelRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class PhotoReelViewModelProvider @Inject constructor(private val repository: PhotoReelRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != PhotoReelViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class in ${this.javaClass}")
        }

        return PhotoReelViewModel(repository) as T
    }
}