package com.ellington.imageloading.impl

import android.graphics.Bitmap
import android.util.LruCache
import com.ellington.imageloading.BitmapCache

class LruBitmapCacheImpl(private val maxSize: Int) : BitmapCache {

    private val cache: LruCache<String, Bitmap>

    init {
        cache = object : LruCache<String, Bitmap>(maxSize) {
            override fun sizeOf(key: String, value: Bitmap): Int {
                return value.byteCount / 1024
            }
        }
    }

    companion object {
        fun getInstance(maxSize: Int): BitmapCache {
            return LruBitmapCacheImpl(maxSize)
        }
    }

    override fun getBitmapFromCache(resourceId: String): Bitmap? {
        return cache[resourceId]
    }

    override fun cacheBitmap(resourceId: String, bitmap: Bitmap?) {
        cache.put(resourceId, bitmap)
    }
}