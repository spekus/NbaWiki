package com.example.nbawiki.ui.main.util.api

import com.example.nbawiki.model.dto.NewsDto
import com.example.nbawiki.model.dto.PlayerDTO
import com.example.nbawiki.model.dto.TeamsGenerated
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class NbaApiService : ApiService {

    val baseUrl: String = "https://www.thesportsdb.com/api/v1/json/1/"

    override suspend fun getTheTeam(id: String): TeamsGenerated {
        return TeamsGenerated()
    }

    override suspend fun getAllTeams(): List<TeamsGenerated> {
        val lookUpUrl = "lookup_all_teams.php/?id=4387"
        val teamsString = makeAPICallWith(baseUrl + lookUpUrl)
        val teams: MutableList<TeamsGenerated> = getTeamsFromString(teamsString)
        return teams
    }



    private suspend fun makeAPICallWith(url: String): String {
        return withContext(Dispatchers.Default) {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.inputStream.bufferedReader().readText()
        }
    }

    private fun getTeamsFromString(teamsString: String): MutableList<TeamsGenerated> {
        var teams: MutableList<com.example.nbawiki.model.dto.TeamsGenerated> =
            mutableListOf<com.example.nbawiki.model.dto.TeamsGenerated>()

        val jsonObject = JSONObject(teamsString)

        val jsonArray = jsonObject.getJSONArray("teams")
        for (i in 0 until jsonArray.length()) {
            teams.add(TeamsGenerated(jsonArray.getJSONObject(i)))
        }
        return teams
    }

    override suspend fun getNews(teamID : String): List<NewsDto>  {
        val lookUpUrl = "eventslast.php?id=$teamID"
        val newsString = makeAPICallWith(baseUrl + lookUpUrl)
        return getNewsFromString(newsString)
    }



    private fun getNewsFromString(newsString: String) : List<NewsDto> {
        var news: MutableList<NewsDto> =
            mutableListOf()

        val jsonObject = JSONObject(newsString)
        val jsonArray = jsonObject.getJSONArray("results")
        for (i in 0 until jsonArray.length()) {
            news.add(NewsDto(jsonArray.getJSONObject(i)))
        }
        return news
    }

    override suspend fun getPlayers(teamName: String) : List<PlayerDTO> {
        val lookUpUrl = "searchplayers.php?t=$teamName"
        val playersString = makeAPICallWith(baseUrl + lookUpUrl)
//        https://www.thesportsdb.com/api/v1/json/1/searchplayers.php?t=Atlanta Hawks
        return getPlayersFromString(playersString)
    }

    private fun getPlayersFromString(playersString: String): List<PlayerDTO> {
        var players: MutableList<PlayerDTO> =
            mutableListOf()

        val jsonObject = JSONObject(playersString)
        val jsonArray = jsonObject.getJSONArray("player")
        for (i in 0 until jsonArray.length()) {
            players.add(PlayerDTO(jsonArray.getJSONObject(i)))
        }
        return players
    }
}