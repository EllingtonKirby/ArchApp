package com.ellington.architecture.dagger

import androidx.lifecycle.ViewModelProvider
import com.ellington.mvvm.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelBuilder {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}