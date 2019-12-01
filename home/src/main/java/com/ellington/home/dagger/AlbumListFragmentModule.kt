package com.ellington.home.dagger

import android.accounts.NetworkErrorException
import androidx.lifecycle.ViewModelProvider
import com.ellington.home.data.Albums
import com.ellington.home.data.source.AlbumsRepository
import com.ellington.home.view.AlbumListFragment
import com.ellington.home.viewmodel.AlbumListViewModel
import com.ellington.home.viewmodel.AlbumListViewModelProvider
import com.ellington.mvvm.repository.Result
import dagger.Module
import dagger.Provides

@Module
abstract class AlbumListFragmentModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providesAlbumListViewModel(
            factory: AlbumListViewModelProvider,
            fragment: AlbumListFragment
        ): AlbumListViewModel {
            return ViewModelProvider(fragment, factory).get(AlbumListViewModel::class.java)
        }

        @JvmStatic
        @Provides
        fun providesAlbumsRepository(): AlbumsRepository {
            return object : AlbumsRepository {
                override suspend fun getAlbums(
                    forcedUpdate: Boolean,
                    nextPage: Boolean
                ): Result<Albums> {
                    return Result.Error(NetworkErrorException())
                }
            }
        }
    }
}