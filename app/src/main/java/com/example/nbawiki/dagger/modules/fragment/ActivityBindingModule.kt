package com.example.nbawiki.dagger.modules.fragment

import com.example.nbawiki.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    abstract fun contributeMainActivity(): MainActivity
}