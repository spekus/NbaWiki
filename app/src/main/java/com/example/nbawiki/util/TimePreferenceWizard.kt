package com.example.nbawiki.util

import android.content.Context
import android.content.SharedPreferences
import com.github.guilhe.sharedprefsutils.ktx.get
import com.github.guilhe.sharedprefsutils.ktx.put
import timber.log.Timber
import java.util.*

class TimePreferenceWizard(context: Context) {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)

    fun isItTimeToUpdate(sharePrefId: String, timeBeforeUpdate: Long): Boolean {
        val lastUpdate = sharedPref.get(sharePrefId, Long::class.java, DEFAULT_VALUE)
        if (lastUpdate == DEFAULT_VALUE) {
            logFailureToRetrieve(sharePrefId)
        }
        val timePassed = System.currentTimeMillis() - lastUpdate
        return timePassed > timeBeforeUpdate
    }

    private fun logFailureToRetrieve(sharePrefId: String) {
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

const val PRIVATE_MODE = 0
const val PREF_NAME = "pref_data"
const val TEAM_PREF_KEY = "team_update_"
const val PLAYER_PREF_KEY = "player_update_"
const val NEWS_PREF_KEY = "news_update_"