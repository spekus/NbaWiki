package com.example.nbawiki.model

data class Team (
    val id : Int ,
    val teamName : String = "DefaultName",
    val teamDescription : String = "DefaultDescription",
    val teamIcon : String = "iconLink",
    val teamMembers : List<Player> = emptyList(),
    val news : List<News> = emptyList(),
    val imageUrl : String = "https://i.picsum.photos/id/$id/200/200.jpg"
)