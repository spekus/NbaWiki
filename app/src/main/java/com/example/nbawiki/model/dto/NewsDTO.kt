package com.example.nbawiki.model.dto

import com.example.nbawiki.model.News
import org.json.JSONObject

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class NewsDTO(
    val idEvent: Int,
    val idSoccerXML: String,
    val idAPIfootball: String,
    val strEvent: String,
    val strEventAlternate: String,
    val strFilename: String,
    val strSport: String,
    val idLeague: Int,
    val strLeague: String,
    val strSeason: Int,
    val strDescriptionEN: String,
    val strHomeTeam: String,
    val strAwayTeam: String,
//    val intHomeScore: Int,
    val intRound: Int,
//    val intAwayScore: Int,
    val intSpectators: String,
    val strHomeGoalDetails: String,
    val strHomeRedCards: String,
    val strHomeYellowCards: String,
    val strHomeLineupGoalkeeper: String,
    val strHomeLineupDefense: String,
    val strHomeLineupMidfield: String,
    val strHomeLineupForward: String,
    val strHomeLineupSubstitutes: String,
    val strHomeFormation: String,
    val strAwayRedCards: String,
    val strAwayYellowCards: String,
    val strAwayGoalDetails: String,
    val strAwayLineupGoalkeeper: String,
    val strAwayLineupDefense: String,
    val strAwayLineupMidfield: String,
    val strAwayLineupForward: String,
    val strAwayLineupSubstitutes: String,
    val strAwayFormation: String,
    val intHomeShots: String,
    val intAwayShots: String,
    val dateEvent: String,
    val dateEventLocal: String,
    val strDate: String,
    val strTime: String,
    val strTimeLocal: String,
    val strTVStation: String,
    val idHomeTeam: Int,
    val idAwayTeam: Int,
    val strResult: String,
    val strCircuit: String,
    val strCountry: String,
    val strCity: String,
    val strPoster: String,
    val strFanart: String,
    val strThumb: String,
    val strBanner: String,
    val strMap: String,
    val strTweet1: String,
    val strTweet2: String,
    val strTweet3: String,
    val strVideo: String,
    val strLocked: String
) {
    constructor(eventJson: JSONObject) : this(
     eventJson.getInt("idEvent") ,
     eventJson.getString("idSoccerXML") ,
     eventJson.getString("idAPIfootball") ,
     eventJson.getString("strEvent") ,
     eventJson.getString("strEventAlternate") ,
     eventJson.getString("strFilename") ,
     eventJson.getString("strSport") ,
     eventJson.getInt("idLeague") ,
     eventJson.getString("strLeague") ,
     eventJson.getInt("strSeason") ,
     eventJson.getString("strDescriptionEN") ,
     eventJson.getString("strHomeTeam") ,
     eventJson.getString("strAwayTeam") ,
//     eventJson.getInt("intHomeScore") ,
     eventJson.getInt("intRound") ,
//     eventJson.getInt("intAwayScore") ,
     eventJson.getString("intSpectators") ,
     eventJson.getString("strHomeGoalDetails") ,
     eventJson.getString("strHomeRedCards") ,
     eventJson.getString("strHomeYellowCards") ,
     eventJson.getString("strHomeLineupGoalkeeper") ,
     eventJson.getString("strHomeLineupDefense") ,
     eventJson.getString("strHomeLineupMidfield") ,
     eventJson.getString("strHomeLineupForward") ,
     eventJson.getString("strHomeLineupSubstitutes") ,
     eventJson.getString("strHomeFormation") ,
     eventJson.getString("strAwayRedCards") ,
     eventJson.getString("strAwayYellowCards") ,
     eventJson.getString("strAwayGoalDetails") ,
     eventJson.getString("strAwayLineupGoalkeeper") ,
     eventJson.getString("strAwayLineupDefense") ,
     eventJson.getString("strAwayLineupMidfield") ,
     eventJson.getString("strAwayLineupForward") ,
     eventJson.getString("strAwayLineupSubstitutes") ,
     eventJson.getString("strAwayFormation") ,
     eventJson.getString("intHomeShots") ,
     eventJson.getString("intAwayShots") ,
     eventJson.getString("dateEvent") ,
     eventJson.getString("dateEventLocal") ,
     eventJson.getString("strDate") ,
     eventJson.getString("strTime") ,
     eventJson.getString("strTimeLocal") ,
     eventJson.getString("strTVStation") ,
     eventJson.getInt("idHomeTeam") ,
     eventJson.getInt("idAwayTeam") ,
     eventJson.getString("strResult") ,
     eventJson.getString("strCircuit") ,
     eventJson.getString("strCountry") ,
     eventJson.getString("strCity") ,
     eventJson.getString("strPoster") ,
     eventJson.getString("strFanart") ,
     eventJson.getString("strThumb") ,
     eventJson.getString("strBanner") ,
     eventJson.getString("strMap") ,
     eventJson.getString("strTweet1") ,
     eventJson.getString("strTweet2") ,
     eventJson.getString("strTweet3") ,
     eventJson.getString("strVideo") ,
     eventJson.getString("strLocked")
    )
}
fun NewsDTO.asPresentationModel() : News {
    return News(
        team = this.strHomeTeam ,
        ennemyTeam = this.strAwayTeam,
        date = this.dateEvent
    )
}