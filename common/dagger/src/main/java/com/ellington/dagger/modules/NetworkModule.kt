package com.ellington.dagger.modules

import com.ellington.dagger.utils.PerApplication
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class NetworkingModule {
    @PerApplication
    @Provides
    fun providesClient(): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
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