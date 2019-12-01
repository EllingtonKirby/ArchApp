package com.ellington.architecture

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.ellington.dagger.modules.DaggerCoreComponent
import kotlin.random.Random

open class ArchApplication : MultiDexApplication() {

    private val coreComponent by lazy {
        DaggerCoreComponent.create()
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context?) =
            (context?.applicationContext as ArchApplication).coreComponent
    }

    override fun onCreate() {
        super.onCreate()

        // Random nightMode to make developer aware of day/night themes
        val nightMode = when (Random.nextBoolean()) {
            true -> AppCompatDelegate.MODE_NIGHT_YES
            false -> AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }
}

fun Activity.coreComponent() = ArchApplication.coreComponent(this)