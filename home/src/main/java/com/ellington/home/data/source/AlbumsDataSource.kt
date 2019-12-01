package com.ellington.home.data.source

import com.ellington.home.data.Albums
import com.ellington.mvvm.repository.Result

interface AlbumsDataSource {
    suspend fun getAlbums(userId: String): Result<Albums>
}