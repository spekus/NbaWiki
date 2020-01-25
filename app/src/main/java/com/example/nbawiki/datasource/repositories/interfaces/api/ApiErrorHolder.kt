package com.example.nbawiki.datasource.repositories.interfaces.api

import androidx.lifecycle.LiveData
import com.example.nbawiki.util.Event

interface ApiErrorHolder {
    val didApiCallFail: LiveData<Event<Boolean>>
}