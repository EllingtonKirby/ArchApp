package com.ellington.imageloading.impl

import android.graphics.BitmapFactory
import android.widget.ImageView
import com.ellington.imageloading.BitmapCache
import com.ellington.imageloading.BitmapPool
import com.ellington.imageloading.ImageLoader
import com.ellington.imageloading.util.ImageLoaderUtilities.canUseForInBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.URL

class ImageLoaderImpl : ImageLoader {

    private val cache: BitmapCache
    private val pool: BitmapPool

    init {
        /**
         * Note: In this example, one eighth of the application memory is allocated for our cache.
         * On a normal/hdpi device this is a minimum of around 4MB (32/8).
         * A full screen GridView filled with images on a device with 800x480 resolution would use around 1.5MB (800*480*4 bytes),
         * so this would cache a minimum of around 2.5 pages of images in memory.
         */
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 8

        pool = LruBitmapPoolImpl.getInstance(cacheSize)
        cache = LruBitmapCacheImpl.getInstance(cacheSize, pool)
    }

    override fun loadImageFromUrl(url: String, imageView: ImageView) {
        CoroutineScope(Dispatchers.IO).launch {

            val reuseBitmap = cache.getBitmapFromCache(url)

            val decodedBitmap = if (reuseBitmap == null) {
                var stream = getInputStream(url)
                val options = BitmapFactory.Options()
                options.inJustDecodeBounds = true
                options.inSampleSize = 1
                BitmapFactory.decodeStream(stream, null, options)

                val inBitmap = pool.get(
                    options.outWidth,
                    options.outHeight,
                    options.inPreferredConfig
                )
                if (inBitmap != null && canUseForInBitmap(inBitmap, options)) {
                    options.inBitmap = inBitmap
                }
                options.inMutable = true
                options.inJustDecodeBounds = false

                stream = getInputStream(url)
                val createdOrReusedBitmap = BitmapFactory.decodeStream(stream, null, options)
                cache.cacheBitmap(url, createdOrReusedBitmap)
                cache.getBitmapFromCache(url)
            } else {
                reuseBitmap
            }

            CoroutineScope(Dispatchers.Main).launch {
                if (decodedBitmap != null) {
                    imageView.setImageBitmap(decodedBitmap)
                }
            }
        }
    }

    override fun putBitmapIntoPool(url: String, id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val toRecycle = cache.recycleBitmap(url)
            if (toRecycle != null) {
                pool.put(toRecycle, toRecycle.width, toRecycle.height, toRecycle.config)
            }
        }
    }

    private fun getInputStream(url: String): InputStream {
        return URL(url).openStream()
    }
}