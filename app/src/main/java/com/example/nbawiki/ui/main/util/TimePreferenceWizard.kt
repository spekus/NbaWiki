package com.example.nbawiki.ui.main.util

import android.content.Context
import android.content.SharedPreferences
import com.example.nbawiki.network.network.PREF_NAME
import com.example.nbawiki.network.network.PRIVATE_MODE
import com.github.guilhe.sharedprefsutils.ktx.get
import com.github.guilhe.sharedprefsutils.ktx.put
import timber.log.Timber
import java.util.*

class TimePreferenceWizard(context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)

    fun isItTimeToUpdate(sharePrefId: String, timeBeforeUpdate: Long): Boolean {
        val lastUpdate = sharedPref.get(sharePrefId, Long::class.java, DEFAULT_VALUE)
        if (lastUpdate == DEFAULT_VALUE) { logFailureToRetrieve(sharePrefId) }
        val timePassed = System.currentTimeMillis() - lastUpdate
        return timePassed > timeBeforeUpdate
    }

    private fun logFailureToRetrieve(sharePrefId : String) {
        Timber.e("could not get shared pref for key $sharePrefId")
    }


    fun updateTimePreferences(key: String, teamId: Int? = null) {
        val currentTime = Date(System.currentTimeMillis()).time
        when (teamId) {
            null -> sharedPref.put(key, currentTime)
            else -> sharedPref.put(key + teamId, currentTime)


        }
    }

    private val DEFAULT_VALUE = 1L
}