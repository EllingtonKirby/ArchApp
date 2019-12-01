package com.ellington.mvvm.base

import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel

interface BaseView<V : ViewModel, T : Any> {

    @get:LayoutRes
    val layoutResourceId: Int

    fun showProgress()

    fun bind(viewModel: V, data: T)
}