package com.example.nbawiki.model.dto.news

import androidx.room.ColumnInfo
import com.example.nbawiki.model.database.NewsDb
import com.example.nbawiki.model.dto.Dto
import com.example.nbawiki.model.presentation.News
import java.text.SimpleDateFormat
import java.util.*

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class NewsDTO(
    val idEvent: Int,
    val idSoccerXML: String?,
    val idAPIfootball: String?,
    val strEvent: String?,
    val strEventAlternate: String?,
    val strFilename: String?,
    val strSport: String?,
    val idLeague: Int?,
    val strLeague: String?,
    val strSeason: Int?,
    val strDescriptionEN: String?,
    val strHomeTeam: String?,
    val strAwayTeam: String?,
    val intRound: Int?,
    val intSpectators: String?,
    val strHomeGoalDetails: String?,
    val strHomeRedCards: String?,
    val strHomeYellowCards: String?,
    val strHomeLineupGoalkeeper: String?,
    val strHomeLineupDefense: String?,
    val strHomeLineupMidfield: String?,
    val strHomeLineupForward: String?,
    val strHomeLineupSubstitutes: String?,
    val strHomeFormation: String?,
    val strAwayRedCards: String?,
    val strAwayYellowCards: String?,
    val strAwayGoalDetails: String?,
    val strAwayLineupGoalkeeper: String?,
    val strAwayLineupDefense: String?,
    val strAwayLineupMidfield: String?,
    val strAwayLineupForward: String?,
    val strAwayLineupSubstitutes: String?,
    val strAwayFormation: String?,
    val intHomeShots: String?,
    val intAwayShots: String?,
    val dateEvent: String?,
    val dateEventLocal: String?,
    val strDate: String?,
    val strTime: String?,
    val strTimeLocal: String?,
    val strTVStation: String?,
    val idHomeTeam: Int?,
    val idAwayTeam: Int?,
    val strResult: String?,
    val strCircuit: String?,
    val strCountry: String?,
    val strCity: String?,
    val strPoster: String?,
    val strFanart: String?,
    val strThumb: String?,
    val strBanner: String?,
    val strMap: String?,
    val strTweet1: String?,
    val strTweet2: String?,
    val strTweet3: String?,
    val strVideo: String?,
    val strLocked: String?
) : Dto {
    fun <T : Dto> T.asPresentationModel(): News {
        val data = this as NewsDTO
        return News(
            team = data.strHomeTeam ?: "",
            ennemyTeam = data.strAwayTeam ?: "",
            date = parseDateToString(
                data.dateEvent ?: ""
            )
        )
    }

    override fun getPresentationModel(): News {
        return this.asPresentationModel()
    }
}

fun NewsDTO.asDatabaseObject(teamId : Int) : NewsDb{
    return NewsDb(
        newsId = this.idEvent,
        homeTeam = this.strHomeTeam ?: "",
        enemyTeam = this.strAwayTeam ?: "",
        date = this.dateEvent ?: "",
        teamId = teamId
    )

}

//@ColumnInfo(name = "DATE") val date : String,
//@ColumnInfo(name = "HOME_TEAM") val homeTeam : String,
//@ColumnInfo(name = "ENEMY_TEAM") val enemyTeam : String,
//@ColumnInfo(name = "TEAM_ID") val teamId : String

private fun parseDateToString(dateAsString: String): String {
    val paternToDisplay = "MMMM d"
    val DtoPatern = "yyyy-MM-dd"
    val date: Date? = SimpleDateFormat(DtoPatern).parse(dateAsString)
    return SimpleDateFormat(paternToDisplay).format(date) ?: ""
}

//fun NewsDTO.asPresentationModel(): News {
//    return News(
//        team = this.strHomeTeam ?: "",
//        ennemyTeam = this.strAwayTeam ?: "",
//        date = parseDateToString(this?.dateEvent ?: "")
//    )
//}
