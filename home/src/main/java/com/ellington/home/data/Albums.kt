package com.ellington.home.data

data class Albums(
    var data: List<Album> = emptyList(),
    var total: Int = 0,
    var next: String = ""
)