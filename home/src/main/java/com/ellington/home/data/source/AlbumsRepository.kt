package com.ellington.home.data.source

import androidx.lifecycle.LiveData
import com.ellington.home.data.Albums
import com.ellington.mvvm.repository.BaseRepository
import com.ellington.mvvm.repository.Result

interface AlbumsRepository : BaseRepository {
    suspend fun getAlbums(userId: String, forcedUpdate: Boolean = false): Result<Albums>
    suspend fun getNextPageOfAlbums(url: String): Result<Albums>
}