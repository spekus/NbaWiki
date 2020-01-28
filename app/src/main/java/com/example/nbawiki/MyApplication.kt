package com.example.nbawiki

import android.app.Application
import com.example.nbawiki.dagger.DaggerMainComponent
import com.example.nbawiki.dagger.MainComponent
import com.example.nbawiki.dagger.modules.ContextModule
import dagger.Component


@Component
interface ApplicationComponent

class MyApplication : Application()  {
    lateinit var component: MainComponent
        private set

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this

        component = DaggerMainComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    companion object {
        private var INSTANCE: MyApplication? = null
        @JvmStatic
        fun get(): MyApplication = INSTANCE!!
    }
}