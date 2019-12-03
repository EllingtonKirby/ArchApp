package com.ellington.home.data.api

import com.ellington.home.data.Albums
import com.ellington.home.data.TrackList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface AlbumsApi {
    @GET("/2.0/user/{user_id}/albums")
    suspend fun getAlbums(@Path("user_id") userId: String): Response<Albums>

    @GET
    suspend fun getNextPageOfAlbums(@Url url: String): Response<Albums>

    @GET
    suspend fun getTrackList(@Url url: String): Response<TrackList>
}