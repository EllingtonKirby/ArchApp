package com.ellington.imageloading.impl

import android.graphics.Bitmap
import android.graphics.Color
import android.util.LruCache
import com.ellington.imageloading.BitmapPool

class LruBitmapPoolImpl(private val maxSize: Int) : BitmapPool {

    private val pool = LruCache<BitmapPoolCacheKey, Bitmap>(maxSize)

    companion object {
        fun getInstance(maxSize: Int): BitmapPool {
            return LruBitmapPoolImpl(maxSize)
        }
    }

    override fun getMaxSize(): Int {
        return maxSize
    }

    override fun get(width: Int, height: Int, config: Bitmap.Config): Bitmap? {
        return pool[BitmapPoolCacheKey(width, height, config)]?.apply {
            eraseColor(Color.TRANSPARENT)
        }
    }

    override fun put(toCache: Bitmap, width: Int, height: Int, config: Bitmap.Config) {
        pool.put(BitmapPoolCacheKey(width, height, config), toCache)
    }

    private data class BitmapPoolCacheKey(
        val width: Int,
        val height: Int,
        val config: Bitmap.Config?
    ) {
        override fun hashCode(): Int {
            var result = width
            result = 31 * result + height
            result = 31 * result + (config?.hashCode() ?: 0)
            return result
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as BitmapPoolCacheKey

            if (width != other.width) return false
            if (height != other.height) return false
            if (config != other.config) return false

            return true
        }
    }
}