package com.ellington.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ellington.home.data.Album

@Database(entities = [Album::class], version = 1, exportSchema = false)
abstract class DeezerDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}