package com.ellington.imageloading

import android.graphics.Bitmap

interface BitmapCache {
    fun getBitmapFromCache(resourceId: String): Bitmap?
    fun cacheBitmap(resourceId: String, bitmap: Bitmap?)
}