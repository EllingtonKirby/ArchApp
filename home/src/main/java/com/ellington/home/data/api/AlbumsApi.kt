package com.ellington.home.data.api

import com.ellington.home.data.Albums
import com.ellington.home.data.TrackList
import io.reactivex.Flowable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface AlbumsApi {
    @GET("/2.0/user/{user_id}/albums")
    fun getAlbums(@Path("user_id") userId: String): Flowable<Result<Albums>>

    @GET
    fun getNextPageOfAlbums(@Url url: String): Flowable<Result<Albums>>

    @GET
    fun getTrackList(@Url url: String): Flowable<Result<TrackList>>
}