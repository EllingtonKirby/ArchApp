package com.ellington.imageloading.impl

import android.graphics.Bitmap
import android.util.LruCache
import com.ellington.imageloading.BitmapCache
import com.ellington.imageloading.BitmapPool

class LruBitmapCacheImpl(private val maxSize: Int, private val pool: BitmapPool) : BitmapCache {

    private val cache: LruCache<String, BitmapReferenceCounter>

    init {
        cache = object : LruCache<String, BitmapReferenceCounter>(maxSize) {
            override fun sizeOf(key: String, value: BitmapReferenceCounter): Int {
                return value.bitmap?.byteCount?.div(1024) ?: 0
            }

            override fun entryRemoved(
                evicted: Boolean,
                key: String?,
                oldValue: BitmapReferenceCounter?,
                newValue: BitmapReferenceCounter?
            ) {
                super.entryRemoved(evicted, key, oldValue, newValue)
                if (evicted) {
                    if (oldValue?.references ?: 0 <= 0) {
                        val toRecycle = oldValue?.bitmap ?: return
                        pool.put(toRecycle, toRecycle.width, toRecycle.height, toRecycle.config)
                    }
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
        val reference = cache[resourceId]
        return if (reference != null) {
            reference.references++
            reference.bitmap
        } else {
            null
        }
    }

    override fun cacheBitmap(resourceId: String, bitmap: Bitmap?) {
        cache.put(resourceId, BitmapReferenceCounter(bitmap))
    }

    override fun recycleBitmap(resourceId: String): Bitmap? {
        val reference = cache[resourceId]
        if (reference != null) {
            reference.references--
        }
        return null
    }

    data class BitmapReferenceCounter(
        var bitmap: Bitmap?,
        var references: Int = 0
    )
}