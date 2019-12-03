package com.ellington.home.dagger.detail

import androidx.lifecycle.ViewModelProvider
import com.ellington.dagger.utils.PerApplication
import com.ellington.home.view.details.AlbumDetailFragment
import com.ellington.home.viewmodel.details.AlbumDetailsViewModel
import com.ellington.home.viewmodel.details.AlbumDetailsViewModelProvider
import dagger.Module
import dagger.Provides

@Module
abstract class AlbumDetailFragmentModule {
    @Module
    companion object {
        @JvmStatic
        @Provides
        @PerApplication
        fun providesAlbumDetailsViewModel(
            factory: AlbumDetailsViewModelProvider,
            fragment: AlbumDetailFragment
        ): AlbumDetailsViewModel {
            return ViewModelProvider(fragment, factory).get(AlbumDetailsViewModel::class.java)
        }
    }
}