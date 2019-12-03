package com.ellington.home.data.source.impl

import com.ellington.home.data.Album
import com.ellington.home.data.Albums
import com.ellington.home.data.TrackList
import com.ellington.home.data.api.AlbumsApi
import com.ellington.home.data.source.AlbumsDataSource
import com.ellington.mvvm.repository.Result
import javax.inject.Inject

class AlbumsDataSourceImpl @Inject constructor(private val api: AlbumsApi) : AlbumsDataSource {
    override suspend fun getAlbums(userId: String): Result<Albums> {
        val result = api.getAlbums(userId)
        return if (result.isSuccessful) {
            Result.Success(result.body() ?: Albums())
        } else {
            Result.Error(Exception(result.message()))
        }
    }

    override suspend fun getNextPageOfAlbums(url: String): Result<Albums> {
        val result = api.getNextPageOfAlbums(url)
        return if (result.isSuccessful) {
            Result.Success(result.body() ?: Albums())
        } else {
            Result.Error(Exception(result.message()))
        }
    }

    override suspend fun getTrackList(url: String): Result<TrackList> {
        val result = api.getTrackList(url)
        return if (result.isSuccessful) {
            Result.Success(result.body() ?: TrackList())
        } else {
            Result.Error(Exception(result.message()))
        }
    }

    override suspend fun getAlbumById(albumId: String): Result<Album> {
        return Result.Error(Exception())
    }

    override suspend fun saveAlbums(albums: Albums) {
        //No-Op
    }
}