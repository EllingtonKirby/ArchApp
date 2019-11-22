package com.ellington.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ellington.mvvm.base.BaseFragment
import javax.inject.Inject

abstract class ViewModelFragment<V : ViewModel> : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: V

    abstract fun observeLiveData()
}