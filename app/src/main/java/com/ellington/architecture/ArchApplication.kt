package com.ellington.architecture

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.ellington.dagger.modules.DaggerCoreComponent
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import kotlin.random.Random

open class ArchApplication : MultiDexApplication() {

    private val coreComponent by lazy {
        DaggerCoreComponent.builder().application(this).build()
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context?) =
            (context?.applicationContext as ArchApplication).coreComponent
    }

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            try {
                ProviderInstaller.installIfNeeded(this)
            } catch (e: GooglePlayServicesRepairableException) {
                GoogleApiAvailability.getInstance()
                    .showErrorNotification(this, e.connectionStatusCode)
                return
            } catch (e: GooglePlayServicesNotAvailableException) {
                return
            }
        }

        // Random nightMode
        val nightMode = when (Random.nextBoolean()) {
            true -> AppCompatDelegate.MODE_NIGHT_YES
            false -> AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }
}

fun Activity.coreComponent() = ArchApplication.coreComponent(this)