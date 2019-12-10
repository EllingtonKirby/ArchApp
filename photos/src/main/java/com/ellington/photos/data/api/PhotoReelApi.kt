package com.ellington.photos.data.api

import com.ellington.photos.data.RandomUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoReelApi {

    @GET("api/")
    suspend fun getRandomUsers(@Query("results") numberOfResults: Int): Response<RandomUserResponse>
}