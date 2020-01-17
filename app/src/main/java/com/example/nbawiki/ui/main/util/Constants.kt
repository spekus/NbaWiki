package com.example.nbawiki.ui.main.util

import com.example.nbawiki.network.network.TeamRepository
import com.example.nbawiki.network.retrofit.Network

object Constants {
    const val ID_OBJECT = "teamID"
    val repository =
        TeamRepository(Network.network)
}