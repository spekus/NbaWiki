package com.example.nbawiki.ui.main.features.teamslist

import com.example.nbawiki.model.database.db.TeamDb

class TeamCard (
    val id : Int,
    val teamName : String = "",
    val teamDescription : String = "",
    val teamIconUrl : String = ""
) {

}

fun List<TeamDb>.asPresentationModel(): List<TeamCard> {
    return  this.map {
        TeamCard(
            id = it.id,
            teamName = it.teamName ?: "",
            teamDescription = it.descriptor ?: "",
            teamIconUrl = it.iconUrl ?: ""
        )
    }
}