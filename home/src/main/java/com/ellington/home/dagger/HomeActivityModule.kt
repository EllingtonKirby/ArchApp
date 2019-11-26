package com.ellington.home.dagger

import com.ellington.home.view.HomeActivity
import com.ellington.home.view.PictureListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class HomeActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeAndroidInjector(): HomeActivity

    @ContributesAndroidInjector(modules = [PictureListFragmentModule::class])
    abstract fun providesPictureListFragment(): PictureListFragment
}