package com.example.nbawiki.model.database.db

import android.annotation.TargetApi
import android.os.Build
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nbawiki.model.dto.players.PlayerDTO
import com.example.nbawiki.ui.main.features.player.Player
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*

@Entity(tableName = "player")
data class PlayerDb (
    @PrimaryKey val playerId : Int,
    @ColumnInfo(name = "NAME_DESCRIPTION") val description: String? = "",
    @ColumnInfo(name = "HEIGHT") val height: String? = "",
    @ColumnInfo(name = "IMAGE_URL") val imageUrl: String? = "",
    @ColumnInfo(name = "NAME") val name: String? = "",
    @ColumnInfo(name = "WEIGHT") val weight: String? = "",
    @ColumnInfo(name = "AGE") val age: String? = "",
    @ColumnInfo(name = "TEAM_ID") val teamId: Int? = 0
) {

}

fun PlayerDb.asRepresentationModel(): Player {
    return Player(
        id = playerId,
        height = clearString(height?.trim()  ?: ""),
        imageUrl = imageUrl ?: "",
        name = name ?: "",
        weight = clearString(weight?.trim()  ?: ""),
        description = description ?: "",
        age = parseAge(age ?: "")
    )
}


fun clearString(str: String): String {
    return str.substringAfter("(").substringBefore(')')
}

@TargetApi(Build.VERSION_CODES.O)
fun parseAge(dateBorn: String): String {
    // NEEDS REWORK - hacky
    val DtoPatern = "yyyy-mm-dd"
    val date: Date? = SimpleDateFormat(DtoPatern).parse(dateBorn)
    val start = LocalDate.of(date!!.year + 1900, date!!.month +1, date!!.date)
    val stop = LocalDate.now(ZoneId.of("America/Montreal"))
    return ChronoUnit.YEARS.between(start, stop).toString()
}