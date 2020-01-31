package com.example.nbawiki.dagger

import com.example.nbawiki.MyApplication
import com.example.nbawiki.dagger.modules.*
import com.example.nbawiki.dagger.modules.fragment.ActivityBindingModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
//    AndroidInjectionModule::class,
    ActivityBindingModule::class,


    RepositoryModule::class,
    NetworkModule::class,
    ContextModule::class,
    CustomViewModelFactoryModule::class,
    CustomViewModelModule::class,
    DataBaseModule::class
])

interface AppComponent : AndroidInjector<MyApplication>

//interface MainComponent {
//    fun inject(playerFragment: PlayerFragment)
//    fun inject(mainFragment: MainFragment)
//    fun inject(teamFragment :TeamFragment)
//    fun inject(newsListFragment: NewsListFragment)
//    fun inject(playerListFragment: PlayerListFragment)
//}