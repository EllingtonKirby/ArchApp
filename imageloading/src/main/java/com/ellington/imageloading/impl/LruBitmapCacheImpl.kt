package com.ellington.imageloading.impl

import android.graphics.Bitmap
import android.util.LruCache
import com.ellington.imageloading.BitmapCache
import com.ellington.imageloading.BitmapPool

class LruBitmapCacheImpl(private val maxSize: Int, private val pool: BitmapPool) : BitmapCache {

    private val cache: LruCache<String, Bitmap>

    init {
        cache = object : LruCache<String, Bitmap>(maxSize) {
            override fun sizeOf(key: String, value: Bitmap): Int {
                return value.byteCount / 1024
            }

            override fun entryRemoved(
                evicted: Boolean,
                key: String?,
                oldValue: Bitmap?,
                newValue: Bitmap?
            ) {
                super.entryRemoved(evicted, key, oldValue, newValue)
                if (evicted) {
                    //When a reference is evicted from the cache, reuse the bitmap
                    pool.put(oldValue ?: return, oldValue.width, oldValue.height, oldValue.config)
                }
            }
        }
    }

    companion object {
        fun getInstance(maxSize: Int, pool: BitmapPool): BitmapCache {
            return LruBitmapCacheImpl(maxSize, pool)
        }
    }

    override fun getBitmapFromCache(resourceId: String): Bitmap? {
        return cache[resourceId]
    }

    override fun cacheBitmap(resourceId: String, bitmap: Bitmap?) {
        cache.put(resourceId, bitmap)
    }
}