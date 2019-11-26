@file:Suppress("UNCHECKED_CAST")

package com.ellington.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class PictureListViewModelProvider @Inject constructor() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != PictureListViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class in ${this.javaClass}")
        }

        return PictureListViewModel() as T
    }
}