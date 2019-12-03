package com.ellington.home.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class Album(
    @PrimaryKey @ColumnInfo(name = "album_id") var id: String = "",
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "link") var link: String = "",
    @ColumnInfo(name = "cover") var cover: String = "",
    @ColumnInfo(name = "cover_small") var coverSmall: String = "",
    @ColumnInfo(name = "cover_medium") var coverMedium: String = "",
    @ColumnInfo(name = "cover_big") var coverBig: String = "",
    @ColumnInfo(name = "cover_xl") var coverXl: String = "",
    @ColumnInfo(name = "release_date") var releaseDate: String = "",
    @ColumnInfo(name = "track_list") var trackList: String = "",
    @Embedded(prefix = "artist_") var artist: Artist = Artist()
)