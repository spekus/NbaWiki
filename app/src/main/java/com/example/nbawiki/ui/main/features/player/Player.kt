package com.example.nbawiki.ui.main.features.player

import android.annotation.TargetApi
import android.os.Build
import androidx.lifecycle.ViewModel
import com.example.nbawiki.model.database.db.PlayerDb
import com.example.nbawiki.model.dto.players.PlayerDTO
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*

data class Player (
    val id : Int = 0,
    val name : String = "defaulName",
    val sureName : String = "defaulSureName",
    val age : String = "",
    val height : String = "",
    val weight : String = "",
    val description : String = "",
    val imageUrl : String = ""
)
