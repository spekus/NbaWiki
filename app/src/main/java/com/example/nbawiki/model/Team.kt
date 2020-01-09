package com.example.nbawiki.model

data class Team (
    val teamName : String = "DefaultName",
    val teamDescription : String = "DefaultDescription",
    val teamIcon : String = "iconLink",
    val teamMembers : List<Player> = emptyList()
)