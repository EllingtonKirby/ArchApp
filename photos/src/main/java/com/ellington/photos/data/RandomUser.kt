package com.ellington.photos.data

data class RandomUser(val picture: Map<String, String> = emptyMap()) {

    companion object {
        const val LARGE = "large"
        const val MEDIUM = "medium"
        const val THUMBNAIL = "thumbnail"
    }

    fun getLargePictureUrl(): String? {
        return picture[LARGE]
    }

    fun getMediumPictureUrl(): String? {
        return picture[MEDIUM]
    }

    fun getThumbnailUrl(): String? {
        return picture[THUMBNAIL]
    }
}