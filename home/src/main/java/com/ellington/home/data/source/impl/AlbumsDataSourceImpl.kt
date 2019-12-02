package com.ellington.home.data.source.impl

import android.util.Log
import com.ellington.home.data.Albums
import com.ellington.home.data.api.AlbumsApi
import com.ellington.home.data.source.AlbumsDataSource
import com.ellington.mvvm.repository.Result
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AlbumsDataSourceImpl @Inject constructor(private val api: AlbumsApi) : AlbumsDataSource {
    override suspend fun getAlbums(userId: String): Flowable<Result<Albums>> {
        return api.getAlbums(userId)
            .subscribeOn(Schedulers.computation())
            .doOnError {
                Log.e("Error", it.message)
            }
            .map {
                if (it.isSuccess) {
                    return@map Result.Success(it.getOrNull() ?: Albums())
                } else {
                    return@map Result.Error(Exception(it.exceptionOrNull()))
                }
            }
            .onErrorReturn { Result.Error(Exception()) }
    }
}