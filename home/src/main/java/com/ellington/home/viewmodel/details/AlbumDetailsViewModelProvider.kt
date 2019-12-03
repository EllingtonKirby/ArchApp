package com.ellington.home.viewmodel.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ellington.dagger.utils.PerApplication
import com.ellington.home.data.source.AlbumsRepository
import javax.inject.Inject

@PerApplication
class AlbumDetailsViewModelProvider @Inject constructor(private var albumsRepository: AlbumsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != AlbumDetailsViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class in ${this.javaClass}")
        }

        return AlbumDetailsViewModel(albumsRepository) as T
    }
}