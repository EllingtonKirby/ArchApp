package com.ellington.home.data.source

import com.ellington.home.data.Albums
import com.ellington.mvvm.repository.BaseRepository
import com.ellington.mvvm.repository.Result

interface AlbumsRepository : BaseRepository {
    suspend fun getAlbums(forcedUpdate: Boolean = false, nextPage: Boolean = false): Result<Albums>
}