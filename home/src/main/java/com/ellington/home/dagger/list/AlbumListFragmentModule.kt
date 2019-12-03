package com.ellington.home.dagger.list

import androidx.lifecycle.ViewModelProvider
import com.ellington.dagger.utils.PerApplication
import com.ellington.home.view.list.AlbumListFragment
import com.ellington.home.viewmodel.AlbumListViewModel
import com.ellington.home.viewmodel.AlbumListViewModelProvider
import dagger.Module
import dagger.Provides

@Module
abstract class AlbumListFragmentModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @PerApplication
        fun providesAlbumListViewModel(
            factory: AlbumListViewModelProvider,
            fragment: AlbumListFragment
        ): AlbumListViewModel {
            return ViewModelProvider(fragment, factory).get(AlbumListViewModel::class.java)
        }
    }
}