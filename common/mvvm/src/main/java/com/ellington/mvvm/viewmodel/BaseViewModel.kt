package com.ellington.mvvm.viewmodel

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    abstract override fun onCleared()
}