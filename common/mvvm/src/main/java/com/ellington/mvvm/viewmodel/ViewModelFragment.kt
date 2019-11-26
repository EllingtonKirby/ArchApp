package com.ellington.mvvm.viewmodel

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ellington.mvvm.base.BaseFragment
import javax.inject.Inject

abstract class ViewModelFragment<V : ViewModel> : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: V

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
    }

    abstract fun observeLiveData()
}