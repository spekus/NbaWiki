package com.example.nbawiki.ui.main.util

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

open class MyViewModel : ViewModel() {
    val coroutineExceptionHandler = CoroutineExceptionHandler{_, t ->
        Timber.e("${t.printStackTrace()}")
        val context = Application().applicationContext
        Toast.makeText(context, "Sorry, something went wrong ", Toast.LENGTH_LONG).show()
    }
}