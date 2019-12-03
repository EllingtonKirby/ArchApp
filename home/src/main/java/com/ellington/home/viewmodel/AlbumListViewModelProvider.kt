@file:Suppress("UNCHECKED_CAST")

package com.ellington.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ellington.home.data.source.AlbumsRepository
import javax.inject.Inject
import javax.inject.Singleton

class AlbumListViewModelProvider @Inject constructor(private var albumsRepository: AlbumsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != AlbumListViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class in ${this.javaClass}")
        }

        return AlbumListViewModel(albumsRepository) as T
    }
}