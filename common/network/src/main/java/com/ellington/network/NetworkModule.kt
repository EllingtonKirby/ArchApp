package com.ellington.network

import android.content.Context
import com.bumptech.glide.Glide
import com.ellington.dagger.utils.PerApplication
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File

@Module
class NetworkingModule {
    @PerApplication
    @Provides
    fun providesCacheDir(application: Context): File =
        File(application.cacheDir, "ImageCache")

    @PerApplication
    @Provides
    fun providesImageCache(file: File): Cache = Cache(file, 1024 * 4)

    @PerApplication
    @Provides
    fun providesGlide(application: Context): Glide = Glide.get(application)

    @PerApplication
    @Provides
    fun providesClient(cache: Cache): OkHttpClient =
        OkHttpClient.Builder().cache(cache).addInterceptor(HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }).build()

    @PerApplication
    @Provides
    fun providesGson(): Gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    // @PerApplication
    // @Provides
    // fun providesRetrofit(
    //     client: OkHttpClient,
    //     gson: Gson
    // ): Retrofit = Retrofit.Builder()
    //     .baseUrl("https://api.openweathermap.org/")
    //     .client(client)
    //     .addConverterFactory(GsonConverterFactory.create(gson))
    //     .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    //     .build()
    //
    // @PerApplication
    // @Provides
    // fun providesApi(retrofit: Retrofit): ApiClient = ApiClient(retrofit.create(Api::class.java))

}