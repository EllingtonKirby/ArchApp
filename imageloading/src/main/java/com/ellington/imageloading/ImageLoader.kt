package com.ellington.imageloading

import android.widget.ImageView

interface ImageLoader {
    fun loadImageFromUrl(url: String, imageView: ImageView)
}