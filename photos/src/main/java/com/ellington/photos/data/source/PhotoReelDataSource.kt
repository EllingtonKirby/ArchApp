package com.ellington.photos.data.source

import com.ellington.mvvm.repository.Result
import com.ellington.photos.data.RandomUserResponse

interface PhotoReelDataSource {
    suspend fun getRandomUsers(numberOfResponses: Int): Result<RandomUserResponse>
}