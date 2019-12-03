package com.ellington.home.data.source.impl

import com.ellington.home.data.Album
import com.ellington.home.data.Albums
import com.ellington.home.data.TrackList
import com.ellington.home.data.local.AlbumDao
import com.ellington.home.data.source.AlbumsDataSource
import com.ellington.mvvm.repository.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlbumLocalDataSourceImpl(
    private val albumDao: AlbumDao, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AlbumsDataSource {
    override suspend fun getAlbums(userId: String): Result<Albums> {
        return Result.Error(Exception())
    }

    override suspend fun getNextPageOfAlbums(url: String): Result<Albums> {
        return Result.Error(Exception())
    }

    override suspend fun getAlbumById(albumId: String): Result<Album> {
        return withContext(ioDispatcher) {
            val album = albumDao.getAlbumById(albumId)
            if (album == null) {
                return@withContext Result.Error(Exception())
            } else {
                return@withContext Result.Success(album)
            }
        }
    }

    override suspend fun saveAlbums(albums: Albums) {
        withContext(ioDispatcher) {
            albumDao.insertAlbums(albums.data)
        }
    }

    override suspend fun getTrackList(url: String): Result<TrackList> {
        return Result.Error(Exception())
    }
}