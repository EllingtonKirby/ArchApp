package com.ellington.photos.data.source

import com.ellington.mvvm.repository.Result
import com.ellington.photos.data.RandomUserResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotoReelRepositoryImpl(
    private val remote: PhotoReelDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PhotoReelRepository {

    override suspend fun getRandomUsers(numberOfResponses: Int): Result<RandomUserResponse> {
        return withContext(ioDispatcher) {
            return@withContext remote.getRandomUsers(numberOfResponses)
        }
    }
}