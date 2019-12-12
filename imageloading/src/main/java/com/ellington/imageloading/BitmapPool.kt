package com.ellington.imageloading

import android.graphics.Bitmap

interface BitmapPool {
    fun getMaxSize(): Int

    fun get(width: Int, height: Int, config: Bitmap.Config): Bitmap?

    fun put(toCache: Bitmap, width: Int, height: Int, config: Bitmap.Config)
}