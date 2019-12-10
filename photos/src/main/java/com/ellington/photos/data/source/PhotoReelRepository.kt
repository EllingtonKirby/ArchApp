package com.ellington.photos.data.source

import com.ellington.mvvm.repository.Result
import com.ellington.photos.data.RandomUserResponse

interface PhotoReelRepository {
    suspend fun getRandomUsers(numberOfResponses: Int): Result<RandomUserResponse>
}