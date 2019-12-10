package com.ellington.photos.data.source

import com.ellington.mvvm.repository.Result
import com.ellington.photos.data.RandomUserResponse
import com.ellington.photos.data.api.PhotoReelApi

class PhotoReelDataSourceImpl(private val api: PhotoReelApi) : PhotoReelDataSource {
    override suspend fun getRandomUsers(numberOfResponses: Int): Result<RandomUserResponse> {
        val response = api.getRandomUsers(numberOfResponses)
        return if (response.isSuccessful) {
            Result.Success(response.body() ?: RandomUserResponse())
        } else {
            Result.Error(Exception(response.message()))
        }
    }
}