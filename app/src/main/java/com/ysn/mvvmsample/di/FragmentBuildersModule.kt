package com.ysn.mvvmsample.di

import com.ysn.mvvmsample.view.ui.ProjectFragment
import com.ysn.mvvmsample.view.ui.ProjectListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProjectListFragment(): ProjectListFragment

    @ContributesAndroidInjector
    abstract fun contributeProjectFragment(): ProjectFragment

}