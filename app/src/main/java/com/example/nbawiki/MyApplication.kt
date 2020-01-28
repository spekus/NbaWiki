package com.example.nbawiki

//import com.example.nbawiki.dagger.MainComponent
import com.example.nbawiki.dagger.DaggerAppComponent
import com.example.nbawiki.dagger.modules.ContextModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }
}