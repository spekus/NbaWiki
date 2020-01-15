package com.example.nbawiki.ui.main.util.api

import com.example.nbawiki.model.dto.NewsDto
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



    private suspend fun makeCallForString(url: String): String {
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
        val newsString = makeCallForString(baseUrl + lookUpUrl)
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
}