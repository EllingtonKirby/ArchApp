package com.ellington.home.data.source.impl

import android.util.Log
import com.ellington.home.data.Albums
import com.ellington.home.data.api.AlbumsApi
import com.ellington.home.data.source.AlbumsDataSource
import com.ellington.mvvm.repository.Result
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AlbumsDataSourceImpl @Inject constructor(private val api: AlbumsApi) : AlbumsDataSource {
    override suspend fun getAlbums(userId: String): Result<Albums> {
        return api.getAlbums(userId)
            .subscribeOn(Schedulers.io())
            .doOnError {
                Log.e("Error", it.message)
            }
            .map {
                if (!it.isError) {
                    return@map Result.Success(it.response()?.body() ?: Albums())
                } else {
                    return@map Result.Error(Exception(it.error()))
                }
            }
            .onErrorReturn { Result.Error(Exception()) }
            .blockingLast()
    }

    override suspend fun getNextPageOfAlbums(url: String): Result<Albums> {
        return api.getNextPageOfAlbums(url)
            .subscribeOn(Schedulers.io())
            .doOnError { Log.e("Error", it.message) }
            .map {
                if (!it.isError) {
                    return@map Result.Success(it.response()?.body() ?: Albums())
                } else {
                    return@map Result.Error(Exception(it.error()))
                }
            }
            .onErrorReturn {
                Result.Error(Exception())
            }
            .blockingLast()
    }
}