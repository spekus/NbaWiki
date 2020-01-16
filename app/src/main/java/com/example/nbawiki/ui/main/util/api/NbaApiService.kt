package com.example.nbawiki.ui.main.util.api

import com.example.nbawiki.model.dto.NewsDTO
import com.example.nbawiki.model.dto.PlayerDTO
import com.example.nbawiki.model.dto.TeamsDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class NbaApiService : ApiService {

    val baseUrl: String = "https://www.thesportsdb.com/api/v1/json/1/"

    override suspend fun getAllTeams(): List<TeamsDTO> {
        val lookUpUrl = "lookup_all_teams.php/?id=4387"
        val teamsString = makeAPICallWith(baseUrl + lookUpUrl)
        val teams: List<TeamsDTO> = getTeamsFromString(teamsString)
        return teams
    }

    private fun getTeamsFromString(teamsString: String): List<TeamsDTO> {
        val jsonObjects: List<JSONObject> = getJson(teamsString, "teams")
        return jsonObjects.map { TeamsDTO(it) }
    }

    private fun getJson(stringToDecode : String ,arrayName: String): List<JSONObject> {
        val jsonArray = JSONObject(stringToDecode).getJSONArray(arrayName)
        val list :MutableList<JSONObject> = mutableListOf<JSONObject>()
        for (i in 0 until jsonArray.length()) {
            list.add(jsonArray.getJSONObject(i))
        }
        return list
    }

    private suspend fun makeAPICallWith(url: String): String {
        return withContext(Dispatchers.Default) {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.inputStream.bufferedReader().readText()
        }
    }

    override suspend fun getNews(teamID : String): List<NewsDTO>  {
        val lookUpUrl = "eventslast.php?id=$teamID"
        val newsString = makeAPICallWith(baseUrl + lookUpUrl)
        return getNewsFromString(newsString)
    }

    private fun getNewsFromString(newsString: String) : List<NewsDTO> {
        val jsonObjects: List<JSONObject> = getJson(newsString, "results")
        return jsonObjects.map { NewsDTO(it) }
    }

    override suspend fun getPlayers(teamName: String) : List<PlayerDTO> {
        val lookUpUrl = "searchplayers.php?t=$teamName"
        val playersString = makeAPICallWith(baseUrl + lookUpUrl)
        return getPlayersFromString(playersString)
    }

    private fun getPlayersFromString(playersString: String): List<PlayerDTO> {
        val jsonObjects: List<JSONObject> = getJson(playersString, "player")
        return jsonObjects.map { PlayerDTO(it) }
    }
}