package com.ellington.dagger.modules

import com.google.gson.Gson
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Component(modules = [NetworkingModule::class])
@Singleton
interface CoreComponent {

    @Component.Builder
    interface Builder {
        fun build(): CoreComponent
    }

    fun providesGson(): Gson
    fun providesOkHttpClient(): OkHttpClient
}