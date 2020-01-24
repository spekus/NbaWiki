package com.example.nbawiki.ui.main.features.team.tabs.players

import com.example.nbawiki.model.database.PlayerDb

data class PlayerListElement (
    val id: Int,
    val name: String = "",
    val icon: String?
){

}



fun  List<PlayerDb>.asPlayerList(): List<PlayerListElement> {
    return this.map {
        PlayerListElement(
            id = it.playerId,
            name = it.name ?: "",
            icon = it.imageUrl ?: ""
        )
    }
}