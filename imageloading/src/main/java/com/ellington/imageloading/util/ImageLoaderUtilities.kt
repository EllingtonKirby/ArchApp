package com.ellington.imageloading.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory

object ImageLoaderUtilities {
    fun canUseForInBitmap(candidate: Bitmap, targetOptions: BitmapFactory.Options): Boolean {
        val width = targetOptions.outWidth / targetOptions.inSampleSize
        val height = targetOptions.outHeight / targetOptions.inSampleSize
        val byteCount: Int = width * height * getBytesPerPixel(
            candidate.config
        )
        return try {
            byteCount <= candidate.allocationByteCount
        } catch (e: NullPointerException) {
            byteCount <= candidate.height * candidate.rowBytes
        }
    }

    fun getBytesPerPixel(config: Bitmap.Config? = Bitmap.Config.ARGB_8888): Int { // A bitmap by decoding a gif has null "config" in certain environments.
        return when (config) {
            Bitmap.Config.ALPHA_8 -> 1
            Bitmap.Config.RGB_565, Bitmap.Config.ARGB_4444 -> 2
            Bitmap.Config.ARGB_8888 -> 4
            else -> 4
        }
    }
}