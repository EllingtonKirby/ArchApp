package com.ellington.imageloading

import android.widget.ImageView

interface ImageLoader {

    fun loadImageFromUrl(url: String, imageView: ImageView)

    // interface Builder {
    //     fun target(view: ImageView): Builder
    //     fun url(url: String): Builder
    //     fun withFade(fadeDurationMs: Int): Builder
    //     fun build()
    // }
}