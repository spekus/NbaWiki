package com.example.nbawiki.ui.main.util

import android.widget.Toast
import com.example.nbawiki.network.network.TeamRepository
import com.example.nbawiki.network.retrofit.Network
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

object Constants {
    const val ID_OBJECT = "teamID"
    val repository =
        TeamRepository(Network.network)

    val coroutineExceptionHandler = CoroutineExceptionHandler{_, t ->
        Timber.e("${t.printStackTrace()}")
    }
}