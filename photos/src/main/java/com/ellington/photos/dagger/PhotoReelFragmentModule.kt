package com.ellington.photos.dagger

import androidx.lifecycle.ViewModelProvider
import com.ellington.dagger.utils.PerApplication
import com.ellington.photos.data.api.PhotoReelApi
import com.ellington.photos.data.source.PhotoReelDataSource
import com.ellington.photos.data.source.PhotoReelDataSourceImpl
import com.ellington.photos.data.source.PhotoReelRepository
import com.ellington.photos.data.source.PhotoReelRepositoryImpl
import com.ellington.photos.view.PhotoReelFragment
import com.ellington.photos.viewmodel.PhotoReelViewModel
import com.ellington.photos.viewmodel.PhotoReelViewModelProvider
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class PhotoReelFragmentModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerApplication
        fun providesAlbumsRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
            return Retrofit.Builder()
                .client(client)
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        @JvmStatic
        @Provides
        @PerApplication
        fun providesAlbumsApi(retrofit: Retrofit): PhotoReelApi {
            return retrofit.create(PhotoReelApi::class.java)
        }

        @JvmStatic
        @Provides
        @PerApplication
        fun providesAlbumsRemoteDataSource(api: PhotoReelApi): PhotoReelDataSource {
            return PhotoReelDataSourceImpl(api)
        }

        @JvmStatic
        @Provides
        @PerApplication
        fun providesAlbumsRepository(
            remoteDataSource: PhotoReelDataSource
        ): PhotoReelRepository {
            return PhotoReelRepositoryImpl(remoteDataSource)
        }

        @JvmStatic
        @Provides
        @PerApplication
        fun providesAlbumDetailsViewModel(
            factory: PhotoReelViewModelProvider,
            fragment: PhotoReelFragment
        ): PhotoReelViewModel {
            return ViewModelProvider(fragment, factory).get(PhotoReelViewModel::class.java)
        }
    }
}
