package com.ellington.mvvm.base

import androidx.lifecycle.ViewModel

interface BaseView<V : ViewModel, T: Any> {
    fun showProgress()

    fun bind(viewModel: V, data: T)
}