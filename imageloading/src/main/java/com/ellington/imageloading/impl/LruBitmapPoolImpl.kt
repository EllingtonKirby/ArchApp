package com.ellington.imageloading.impl

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.util.LruCache
import com.ellington.imageloading.BitmapPool
import com.ellington.imageloading.R
import java.util.Queue
import java.util.concurrent.ConcurrentLinkedQueue

class LruBitmapPoolImpl(private val maxSize: Int) : BitmapPool {

    private val pool = LruCache<BitmapPoolCacheKey, Queue<Bitmap>>(maxSize)

    companion object {
        fun getInstance(maxSize: Int): BitmapPool {
            return LruBitmapPoolImpl(maxSize)
        }
    }

    override fun getMaxSize(): Int {
        return maxSize
    }

    override fun get(width: Int, height: Int, config: Bitmap.Config): Bitmap? {
        return pool[BitmapPoolCacheKey(width, height, config)]?.poll()
    }

    override fun put(toCache: Bitmap, width: Int, height: Int, config: Bitmap.Config) {
        val key = BitmapPoolCacheKey(width, height, config)
        toCache.eraseColor(Color.TRANSPARENT)
        if (pool[key] != null) {
            pool[key]?.add(toCache)
        } else {
            pool.put(key, ConcurrentLinkedQueue<Bitmap>().apply { this.add(toCache) })
        }
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