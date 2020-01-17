package com.example.nbawiki.model.dto.players

import android.annotation.TargetApi
import android.os.Build
import com.example.nbawiki.model.dto.Dto
import com.example.nbawiki.model.presentation.Player
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class PlayerDTO(
    val idPlayer: Int,
    val idTeam: Int?,
    val idTeam2: String?,
    val idTeamNational: String?,
    val idAPIfootball: String?,
    val idPlayerManager: String?,
    val strNationality: String?,
    val strPlayer: String?,
    val strTeam: String?,
    val strTeam2: String?,
    val strSport: String?,
    val intSoccerXMLTeamID: String?,
    val dateBorn: String?,
    val strNumber: String?,
    val dateSigned: String?,
    val strSigning: String?,
    val strWage: String?,
    val strOutfitter: String?,
    val strKit: String?,
    val strAgent: String?,
    val strBirthLocation: String?,
    val strDescriptionEN: String?,
    val strDescriptionDE: String?,
    val strDescriptionFR: String?,
    val strDescriptionCN: String?,
    val strDescriptionIT: String?,
    val strDescriptionJP: String?,
    val strDescriptionRU: String?,
    val strDescriptionES: String?,
    val strDescriptionPT: String?,
    val strDescriptionSE: String?,
    val strDescriptionNL: String?,
    val strDescriptionHU: String?,
    val strDescriptionNO: String?,
    val strDescriptionIL: String?,
    val strDescriptionPL: String?,
    val strGender: String?,
    val strSide: String?,
    val strPosition: String?,
    val strCollege: String?,
    val strFacebook: String?,
    val strWebsite: String?,
    val strTwitter: String?,
    val strInstagram: String?,
    val strYoutube: String?,
    val strHeight: String?,
    val strWeight: String?,
    val intLoved: Int?,
    val strThumb: String?,
    val strCutout: String?,
    val strRender: String?,
    val strBanner: String?,
    val strFanart1: String?,
    val strFanart2: String?,
    val strFanart3: String?,
    val strFanart4: String?,
    val strCreativeCommons: String?,
    val strLocked: String?
) : Dto {
    override fun <T : Dto> T.asPresentationModel(): Player {
        val data = this as PlayerDTO
        return Player(
            id = data.idPlayer,
            name = data.strPlayer?.trim() ?: "",
            sureName = data.strNationality?.trim() ?: "",
            height = clearString(
                data?.strHeight ?: ""
            ),
            weight = clearString(
                data?.strWeight ?: ""
            ),
            age = parseAge(
                data?.dateBorn ?: ""
            ),
            description = data?.strDescriptionEN ?: "",
            imageUrl = data?.strThumb ?: ""
        )
    }

    override fun getPresentationModel() : Player {
        return this.asPresentationModel()
    }

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

//
//fun PlayerDTO.asPresentationModel(): Player {
//    return Player(
//        id = this.idPlayer,
//        name = this.strPlayer?.trim() ?: "",
//        sureName = this.strNationality?.trim() ?: "",
//        height = clearString(this?.strHeight ?: "") ,
//        weight = clearString(this?.strWeight ?: ""),
//        age = parseAge(this?.dateBorn ?: ""),
//        description = this?.strDescriptionEN ?: "",
//        imageUrl = this?.strThumb ?: ""
//    )
//}