package com.ellington.home.data

data class Album(
    var id: String = "",
    var title: String = "",
    var cover: String = "",
    var coverSmall: String = "",
    var coverMedium: String = "",
    var coverBig: String = "",
    var coverXl: String = "",
    var artist: Artist = Artist()
)