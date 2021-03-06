package com.example.nbawiki.model.dto.players

import android.annotation.TargetApi
import android.os.Build
import com.example.nbawiki.model.database.db.PlayerDb
import com.example.nbawiki.ui.main.features.player.Player
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
)  {
}


fun PlayerDTO.asDataBaseObject(teamId : Int ) : PlayerDb {
    return PlayerDb(
        playerId = this.idPlayer,
        height = this.strHeight,
        imageUrl = this.strThumb,
        name = this.strPlayer,
        weight = this.strWeight,
        age = this.dateBorn, // this needs adjustment
        description = this.strDescriptionEN,
        teamId = teamId
    )
}
