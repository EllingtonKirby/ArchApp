package com.ellington.home.data.source.impl

import com.ellington.home.data.Albums
import com.ellington.home.data.source.AlbumsDataSource
import com.ellington.home.data.source.AlbumsRepository
import com.ellington.mvvm.repository.Result

class AlbumsRepositoryImpl(val albumsDataSource: AlbumsDataSource) : AlbumsRepository {
    override suspend fun getAlbums(
        userId: String,
        forcedUpdate: Boolean,
        nextPage: Boolean
    ): Result<Albums> {
        return albumsDataSource.getAlbums(userId).blockingFirst()
    }
}