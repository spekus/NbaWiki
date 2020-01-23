package com.example.nbawiki.model.presentation

import androidx.lifecycle.ViewModel
import java.util.*

data class Player (
    val id : Int = 0,
    val name : String = "defaulName",
    val sureName : String = "defaulSureName",
    val age : String = "",
    val height : String = "",
    val weight : String = "",
    val description : String = "",
    val imageUrl : String = "https://i.picsum.photos/id/$id/300/300.jpg"
) : PresenationModel
