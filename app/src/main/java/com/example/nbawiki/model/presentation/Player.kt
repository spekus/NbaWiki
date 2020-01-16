package com.example.nbawiki.model.presentation

import java.util.*

data class Player (
    val id : Int = 0,
    val name : String = "defaulName",
    val sureName : String = "defaulSureName",
    val age : String = (20..50).random().toString(),
    val height : String = (160..250).random().toString(),
    val weight : String = (80..150).random().toString(),
    val description : String = UUID.randomUUID().toString(),
    val imageUrl : String = "https://i.picsum.photos/id/$id/300/300.jpg"
)
