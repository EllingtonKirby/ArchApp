package com.ellington.home.data.source

import com.ellington.home.data.Album
import com.ellington.home.data.Albums
import com.ellington.home.data.TrackList
import com.ellington.mvvm.repository.BaseRepository
import com.ellington.mvvm.repository.Result

interface AlbumsRepository : BaseRepository {
    suspend fun getAlbums(userId: String, forcedUpdate: Boolean = false): Result<Albums>
    suspend fun getNextPageOfAlbums(url: String): Result<Albums>
    suspend fun getAlbumById(albumId: String): Result<Album>
    suspend fun getTrackList(url: String): Result<TrackList>
}