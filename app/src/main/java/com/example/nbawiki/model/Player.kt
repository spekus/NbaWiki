package com.example.nbawiki.model

import java.util.*

data class Player (
    val id : Int = 0,
    val name : String = "defaulName",
    val sureName : String = "defaulSureName",
    val age : String = (20..50).random().toString(),
    val height : Int = (160..250).random(),
    val weight : Int = (80..150).random(),
    val description : String = UUID.randomUUID().toString()
)
