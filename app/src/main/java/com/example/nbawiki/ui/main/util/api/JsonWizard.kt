package com.example.nbawiki.ui.main.util.api

import com.example.nbawiki.model.dto.NewsDTO
import com.example.nbawiki.model.dto.PlayerDTO
import com.example.nbawiki.model.dto.TeamsDTO
import org.json.JSONObject

object JsonWizard {
    fun mapTeamsFromString(teamsString: String): List<TeamsDTO> {
        val jsonObjects: List<JSONObject> = getJson(teamsString, "teams")
        return jsonObjects.map { TeamsDTO(it) }
    }

    fun mapNewsFromString(newsString: String): List<NewsDTO> {
        val jsonObjects: List<JSONObject> = getJson(newsString, "results")
        return jsonObjects.map { NewsDTO(it) }
    }


    fun mapPlayersFromString(playersString: String): List<PlayerDTO> {
        val jsonObjects: List<JSONObject> = getJson(playersString, "player")
        return jsonObjects.map { PlayerDTO(it) }
    }

    private fun getJson(stringToDecode: String, arrayName: String): List<JSONObject> {
        val jsonArray = JSONObject(stringToDecode).getJSONArray(arrayName)
        val list: MutableList<JSONObject> = mutableListOf()
        for (i in 0 until jsonArray.length()) {
            list.add(jsonArray.getJSONObject(i))
        }
        return list
    }
}

