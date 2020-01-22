package com.example.nbawiki.network.network.repointerfaces.api

import androidx.lifecycle.LiveData
import com.example.nbawiki.network.network.repointerfaces.Repository
import com.example.nbawiki.ui.main.util.Event

interface ApiRepository : Repository {
    val didApiCallFail: LiveData<Event<Boolean>>
}