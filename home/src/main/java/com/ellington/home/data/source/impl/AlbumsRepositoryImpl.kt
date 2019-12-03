package com.ellington.home.data.source.impl

import com.ellington.home.data.Album
import com.ellington.home.data.Albums
import com.ellington.home.data.source.AlbumsDataSource
import com.ellington.home.data.source.AlbumsRepository
import com.ellington.mvvm.repository.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlbumsRepositoryImpl(
    private val remoteDataSource: AlbumsDataSource,
    private val localDataSource: AlbumsDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AlbumsRepository {

    override suspend fun getAlbums(
        userId: String,
        forcedUpdate: Boolean
    ): Result<Albums> {
        return withContext(ioDispatcher) {
            val remote = remoteDataSource.getAlbums(userId)
            if (remote is Result.Success) {
                refreshLocalDataSource(remote.data)
            }

            return@withContext remote
        }
    }

    override suspend fun getNextPageOfAlbums(url: String): Result<Albums> {
        return withContext(ioDispatcher) {
            val remote = remoteDataSource.getNextPageOfAlbums(url)
            if (remote is Result.Success) {
                refreshLocalDataSource(remote.data)
            }

            return@withContext remote
        }
    }

    override suspend fun getAlbumById(albumId: String): Result<Album> {
        return localDataSource.getAlbumById(albumId)
    }

    private suspend fun refreshLocalDataSource(albums: Albums) {
        localDataSource.saveAlbums(albums)
    }
}