package com.ellington.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ellington.home.data.Album

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(albums: List<Album>)

    @Query("SELECT * From albums WHERE album_id = :albumId")
    suspend fun getAlbumById(albumId: String): Album?
}