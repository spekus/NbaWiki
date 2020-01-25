package com.example.nbawiki.model.presentation

import com.example.nbawiki.ui.main.features.player.Player

class Team (
    val id : Int,
    val teamName : String = "",
    val teamDescription : String = "",
    val teamIconUrl : String = "",
    var teamMembers : List<Player> = emptyList(),
    var news : List<News> = emptyList(),
    val imageUrl : String = ""
)