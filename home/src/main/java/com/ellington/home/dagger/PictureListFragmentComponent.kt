package com.ellington.home.dagger

import com.ellington.dagger.modules.BaseFragmentComponent
import com.ellington.dagger.modules.CoreComponent
import com.ellington.dagger.utils.PerActivity
import com.ellington.home.view.PictureListFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [PictureListFragmentModule::class], dependencies = [CoreComponent::class])
@PerActivity
interface PictureListFragmentComponent : BaseFragmentComponent<PictureListFragment> {

    @Component.Builder
    interface Builder {
        fun build(): PictureListFragmentComponent
        @BindsInstance
        fun pictureListFragment(fragment: PictureListFragment): Builder

        fun coreComponent(module: CoreComponent): Builder
    }
}