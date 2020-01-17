package com.example.nbawiki.ui.main.util

import com.example.nbawiki.model.TeamRepository
import com.example.nbawiki.ui.main.util.api.retrofit.Network

object Constants {
    const val ID_OBJECT = "teamID"
    val repository = TeamRepository(Network.network)
}