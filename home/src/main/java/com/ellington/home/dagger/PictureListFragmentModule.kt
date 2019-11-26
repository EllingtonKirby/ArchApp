package com.ellington.home.dagger

import androidx.lifecycle.ViewModelProvider
import com.ellington.home.view.PictureListFragment
import com.ellington.home.viewmodel.PictureListViewModel
import com.ellington.home.viewmodel.PictureListViewModelProvider
import dagger.Module
import dagger.Provides

@Module
abstract class PictureListFragmentModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providesPictureListViewModel(
            factory: PictureListViewModelProvider,
            fragment: PictureListFragment
        ): PictureListViewModel {
            return ViewModelProvider(fragment, factory).get(PictureListViewModel::class.java)
        }
    }
}