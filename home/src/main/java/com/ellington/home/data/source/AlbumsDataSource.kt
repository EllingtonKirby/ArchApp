package com.ellington.home.data.source

import com.ellington.home.data.Album
import com.ellington.home.data.Albums
import com.ellington.mvvm.repository.Result

interface AlbumsDataSource {
    suspend fun getAlbums(userId: String): Result<Albums>
    suspend fun getNextPageOfAlbums(url: String): Result<Albums>
    suspend fun getAlbumById(albumId: String): Result<Album>
    suspend fun saveAlbums(albums: Albums)
}