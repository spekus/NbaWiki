package com.example.nbawiki.ui.main.util.api

import com.example.nbawiki.model.dto.TeamsGenerated
import com.example.nbawiki.model.dto.TeamDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class NbaApiService : ApiService {

    val baseUrl: String = "https://www.thesportsdb.com/api/v1/json/1/"

    override fun getATeams(id: String): TeamDTO {
        return TeamDTO("")
    }

    override suspend fun getAllteams(): List<TeamsGenerated> {
        val lookUpUrl = "lookup_all_teams.php/?id=4387"
        val teamsString = makeCallForString(baseUrl + lookUpUrl)
        val teams: MutableList<TeamsGenerated> = getTeamsFromString(teamsString)
        return teams
    }

    suspend fun makeCallForString(url: String): String {
        return withContext(Dispatchers.Default) {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.inputStream.bufferedReader().readText()
        }
    }

    fun getTeamsFromString(teamsString: String): MutableList<TeamsGenerated> {
        var teams: MutableList<com.example.nbawiki.model.dto.TeamsGenerated> =
            mutableListOf<com.example.nbawiki.model.dto.TeamsGenerated>()

        val jsonObject = JSONObject(teamsString)

        val jsonArray = jsonObject.getJSONArray("teams")
        for (i in 0 until jsonArray.length()) {
            teams.add(createTeamFromJson(jsonArray.getJSONObject(i)))
        }

        return teams
    }

    private fun createTeamFromJson(jsonObject: JSONObject): TeamsGenerated {
        return TeamsGenerated(
            jsonObject.getInt("idTeam"),
            jsonObject.getString("idSoccerXML"),
            jsonObject.getInt("idAPIfootball"),
            jsonObject.getString("intLoved"),
            jsonObject.getString("strTeam"),
            jsonObject.getString("strTeamShort"),
            jsonObject.getString("strAlternate"),
            jsonObject.getInt("intFormedYear"),
            jsonObject.getString("strSport"),
            jsonObject.getString("strLeague"),
            jsonObject.getInt("idLeague"),
            jsonObject.getString("strDivision"),
            jsonObject.getString("strManager"),
            jsonObject.getString("strStadium"),
            jsonObject.getString("strKeywords"),
            jsonObject.getString("strRSS"),
            jsonObject.getString("strStadiumThumb"),
            jsonObject.getString("strStadiumDescription"),
            jsonObject.getString("strStadiumLocation"),
            jsonObject.getInt("intStadiumCapacity"),
            jsonObject.getString("strWebsite"),
            jsonObject.getString("strFacebook"),
            jsonObject.getString("strTwitter"),
            jsonObject.getString("strInstagram"),
            jsonObject.getString("strDescriptionEN"),
            jsonObject.getString("strDescriptionDE"),
            jsonObject.getString("strDescriptionFR"),
            jsonObject.getString("strDescriptionCN"),
            jsonObject.getString("strDescriptionIT"),
            jsonObject.getString("strDescriptionJP"),
            jsonObject.getString("strDescriptionRU"),
            jsonObject.getString("strDescriptionES"),
            jsonObject.getString("strDescriptionPT"),
            jsonObject.getString("strDescriptionSE"),
            jsonObject.getString("strDescriptionNL"),
            jsonObject.getString("strDescriptionHU"),
            jsonObject.getString("strDescriptionNO"),
            jsonObject.getString("strDescriptionIL"),
            jsonObject.getString("strDescriptionPL"),
            jsonObject.getString("strGender"),
            jsonObject.getString("strCountry"),
            jsonObject.getString("strTeamBadge"),
            jsonObject.getString("strTeamJersey"),
            jsonObject.getString("strTeamLogo"),
            jsonObject.getString("strTeamFanart1"),
            jsonObject.getString("strTeamFanart2"),
            jsonObject.getString("strTeamFanart3"),
            jsonObject.getString("strTeamFanart4"),
            jsonObject.getString("strTeamBanner"),
            jsonObject.getString("strYoutube"),
            jsonObject.getString("strLocked")
        )
    }
}