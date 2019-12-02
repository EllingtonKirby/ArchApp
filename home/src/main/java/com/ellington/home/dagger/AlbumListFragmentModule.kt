package com.ellington.home.dagger

import androidx.lifecycle.ViewModelProvider
import com.ellington.home.data.api.AlbumsApi
import com.ellington.home.data.source.AlbumsDataSource
import com.ellington.home.data.source.AlbumsRepository
import com.ellington.home.data.source.impl.AlbumsDataSourceImpl
import com.ellington.home.data.source.impl.AlbumsRepositoryImpl
import com.ellington.home.view.AlbumListFragment
import com.ellington.home.viewmodel.AlbumListViewModel
import com.ellington.home.viewmodel.AlbumListViewModelProvider
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
abstract class AlbumListFragmentModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providesAlbumsRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
            return Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.deezer.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        @JvmStatic
        @Provides
        fun providesAlbumsApi(retrofit: Retrofit): AlbumsApi {
            return retrofit.create(AlbumsApi::class.java)
        }

        @JvmStatic
        @Provides
        fun providesAlbumsDataSource(api: AlbumsApi): AlbumsDataSource {
            return AlbumsDataSourceImpl(api)
        }

        @JvmStatic
        @Provides
        fun providesAlbumsRepository(dataSource: AlbumsDataSource): AlbumsRepository {
            return AlbumsRepositoryImpl(dataSource)
        }

        @JvmStatic
        @Provides
        fun providesAlbumListViewModel(
            factory: AlbumListViewModelProvider,
            fragment: AlbumListFragment
        ): AlbumListViewModel {
            return ViewModelProvider(fragment, factory).get(AlbumListViewModel::class.java)
        }
    }
}