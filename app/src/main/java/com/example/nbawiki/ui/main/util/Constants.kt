package com.example.nbawiki.ui.main.util

import com.example.nbawiki.model.TeamRepository
import com.example.nbawiki.ui.main.util.api.NbaApiService

object Constants {

    const val ARG_OBJECT = "object"
    const val ID_OBJECT = "teamID"
    val repository = TeamRepository(NbaApiService())

}