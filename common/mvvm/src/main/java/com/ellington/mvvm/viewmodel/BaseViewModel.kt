package com.ellington.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseViewModel<T : ViewModelProvider.Factory> : ViewModel() {
    abstract override fun onCleared()
}