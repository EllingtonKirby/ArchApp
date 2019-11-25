package com.ellington.architecture

import android.app.Activity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.ellington.dagger.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import kotlin.random.Random

open class ArchApplication: DaggerApplication(), HasActivityInjector, HasSupportFragmentInjector {
    /**
     * Wrappers around mappings of classes descended from android components to relevant injectors
     * Mapping is assembled via @ContributesAndroidInjector annotated methods
     */
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return activityInjector
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerAppComponent
            .builder()
            .application(this)
            .build()
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