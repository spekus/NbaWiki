package com.example.nbawiki.model.dto.teams

import com.example.nbawiki.model.database.db.TeamDb

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */

data class TeamDTO(
    var idTeam: Int? = 0,
    val idSoccerXML: String? = "",
    val idAPIfootball: Int? = 0,
    val intLoved: String? = "",
    val strTeam: String? = "",
    val strTeamShort: String? = "",
    val strAlternate: String? = "",
    val intFormedYear: Int? = 0,
    val strSport: String? = "",
    val strLeague: String? = "",
    val idLeague: Int? = 0,
    val strDivision: String? = "",
    val strManager: String? = "",
    val strStadium: String? = "",
    val strKeywords: String? = "",
    val strRSS: String? = "",
    val strStadiumThumb: String? = "",
    val strStadiumDescription: String? = "",
    val strStadiumLocation: String? = "",
    val intStadiumCapacity: Int? = 0,
    val strWebsite: String? = "",
    val strFacebook: String? = "",
    val strTwitter: String? = "",
    val strInstagram: String? = "",
    val strDescriptionEN: String? = "",
    val strDescriptionDE: String? = "",
    val strDescriptionFR: String? = "",
    val strDescriptionCN: String? = "",
    val strDescriptionIT: String? = "",
    val strDescriptionJP: String? = "",
    val strDescriptionRU: String? = "",
    val strDescriptionES: String? = "",
    val strDescriptionPT: String? = "",
    val strDescriptionSE: String? = "",
    val strDescriptionNL: String? = "",
    val strDescriptionHU: String? = "",
    val strDescriptionNO: String? = "",
    val strDescriptionIL: String? = "",
    val strDescriptionPL: String? = "",
    val strGender: String? = "",
    val strCountry: String? = "",
    val strTeamBadge: String? = "",
    val strTeamJersey: String? = "",
    val strTeamLogo: String? = "",
    val strTeamFanart1: String? = "",
    val strTeamFanart2: String? = "",
    val strTeamFanart3: String? = "",
    val strTeamFanart4: String? = "",
    val strTeamBanner: String? = "",
    val strYoutube: String? = "",
    val strLocked: String? = ""
) {
}

fun TeamDTO.asDBModel(): TeamDb {
    return TeamDb(
        id = this.idTeam ?: 0,
        teamName = this.strTeam?.trim() ?: "",
        iconUrl = this.strTeamBadge ?: "",
        imageUrl = this.strStadiumThumb ?: "",
        descriptor = this.strDescriptionEN ?: ""
    )
}