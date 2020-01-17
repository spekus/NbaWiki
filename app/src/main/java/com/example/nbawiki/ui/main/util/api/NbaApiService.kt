package com.example.nbawiki.ui.main.util.api

import com.example.nbawiki.model.dto.NewsDTO
import com.example.nbawiki.model.dto.PlayerDTO
import com.example.nbawiki.model.dto.TeamDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class NbaApiService : ApiService {
    private val BASE_URL: String = "https://www.thesportsdb.com/api/v1/json/1/"

    override suspend fun getAllTeams(): List<TeamDTO> {
        val lookUpUrl = "lookup_all_teams.php/?id=4387"
        val teamsString = makeAPICallWith(BASE_URL + lookUpUrl)
        return JsonWizard.mapTeamsFromString(teamsString)
    }

    override suspend fun getNews(teamID : String): List<NewsDTO>  {
        val lookUpUrl = "eventslast.php?id=$teamID"
        val newsString = makeAPICallWith(BASE_URL + lookUpUrl)
        return JsonWizard.mapNewsFromString(newsString)
    }

    override suspend fun getPlayers(teamName: String) : List<PlayerDTO> {
        val lookUpUrl = "searchplayers.php?t=$teamName"
        val playersString = makeAPICallWith(BASE_URL + lookUpUrl)
        return JsonWizard.mapPlayersFromString(playersString)
    }

    private suspend fun makeAPICallWith(url: String): String {
        return withContext(Dispatchers.Default) {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.inputStream.bufferedReader().readText()
        }
    }
}