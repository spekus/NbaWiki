package com.example.nbawiki.ui.main.features.team.models

import com.example.nbawiki.model.database.db.PlayerDb

data class PlayerListElement (
    val id: Int,
    val name: String = "",
    val icon: String?
){

}



fun  List<PlayerDb>.asPlayerListItem(): List<PlayerListElement> {
    return this.map {
        PlayerListElement(
            id = it.playerId,
            name = it.name ?: "",
            icon = it.imageUrl ?: ""
        )
    }
}