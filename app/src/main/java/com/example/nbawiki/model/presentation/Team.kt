package com.example.nbawiki.model.presentation

data class Team (
    val id : Int,
    val teamName : String = "DefaultName",
    val teamDescription : String = "DefaultDescription",
    val teamIconUrl : String = "iconLink",
    var teamMembers : List<Player> = emptyList(),
    var news : List<News> = emptyList(),
    val imageUrl : String = "https://i.picsum.photos/id/$id/200/200.jpg"
) : PresenationModel