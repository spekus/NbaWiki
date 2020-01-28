package com.example.nbawiki.dagger.modules

import android.content.Context
import androidx.room.Room
import com.example.nbawiki.datasource.database.AppDatabase
import com.example.nbawiki.datasource.retrofit.WebService
import com.example.nbawiki.model.database.dao.NewDao
import com.example.nbawiki.model.database.dao.PlayerDao
import com.example.nbawiki.model.database.dao.TeamsDao
import com.example.nbawiki.util.TimePreferenceWizard
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//@Module(includes = [InterceptorModule::class])
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideWebService(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)

    @Provides
    @Singleton
    fun ProvideGson() : Gson = GsonBuilder()
            .setLenient()
            .create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, baseUrl : String): Retrofit=
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideBaseUrl() : String = "https://www.thesportsdb.com"


}
