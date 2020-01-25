package com.example.nbawiki.ui.main.features.team.models

import com.example.nbawiki.model.database.db.TeamDb

data class TeamDetails(
    val name : String = "",
    val imageUrl : String = ""
) {
}

fun TeamDb.asTeamDetailsModel(): TeamDetails {
    return  TeamDetails(
        name = this.teamName ?: "",
        imageUrl = this.imageUrl ?: ""
    )
}