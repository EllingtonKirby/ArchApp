package com.ellington.home.dagger

import android.app.Application
import androidx.room.Room
import com.ellington.dagger.utils.PerApplication
import com.ellington.home.data.api.AlbumsApi
import com.ellington.home.data.local.AlbumDao
import com.ellington.home.data.local.DeezerDatabase
import com.ellington.home.data.source.AlbumsDataSource
import com.ellington.home.data.source.AlbumsRepository
import com.ellington.home.data.source.impl.AlbumLocalDataSourceImpl
import com.ellington.home.data.source.impl.AlbumsDataSourceImpl
import com.ellington.home.data.source.impl.AlbumsRepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
abstract class CommonAlbumsModule {
    @Module
    companion object {
        @JvmStatic
        @Provides
        @PerApplication
        fun providesAlbumsRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
            return Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.deezer.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        @JvmStatic
        @Provides
        @PerApplication
        fun providesAlbumsApi(retrofit: Retrofit): AlbumsApi {
            return retrofit.create(AlbumsApi::class.java)
        }

        @JvmStatic
        @Provides
        @Named("remote")
        @PerApplication
        fun providesAlbumsRemoteDataSource(api: AlbumsApi): AlbumsDataSource {
            return AlbumsDataSourceImpl(api)
        }

        @JvmStatic
        @Provides
        @PerApplication
        fun providesAlbumsDao(application: Application): AlbumDao {
            val db = Room.databaseBuilder(
                application,
                DeezerDatabase::class.java,
                "deezer_database"
            )
                .build()
            return db.albumDao()
        }

        @JvmStatic
        @Provides
        @Named("local")
        @PerApplication
        fun providesAlbumsLocalDataSource(albumDao: AlbumDao): AlbumsDataSource {
            return AlbumLocalDataSourceImpl(albumDao)
        }

        @JvmStatic
        @Provides
        @PerApplication
        fun providesAlbumsRepository(
            @Named("remote")
            remoteDataSource: AlbumsDataSource,
            @Named("local")
            localDataSource: AlbumsDataSource
        ): AlbumsRepository {
            return AlbumsRepositoryImpl(remoteDataSource, localDataSource)
        }
    }
}