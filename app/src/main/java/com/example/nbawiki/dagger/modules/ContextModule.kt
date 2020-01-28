package com.example.nbawiki.dagger.modules

import android.content.Context
import com.example.nbawiki.util.TimePreferenceWizard
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private val appContext : Context) {
    @Provides
    fun appContext() : Context = appContext
}