package com.example.nbawiki.ui.main.util.api

import android.util.Log
import com.example.nbawiki.model.dto.TeamDTO
import java.net.HttpURLConnection
import java.net.URL

class NbaApiService : ApiService {
    override fun getATeams(id: String): TeamDTO {
        return makeCall("1")
    }

    private fun makeCall(s: String): TeamDTO {
        var textas  =""
        Thread {
           val connection = URL("https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php/?id=4387").openConnection() as HttpURLConnection
           val data = connection.inputStream.bufferedReader().readText()
           Log.e("TeamRepository",data )
            textas = connection.inputStream.bufferedReader().readText()
       }.start()

        return TeamDTO("1")
    }

    override fun getAllteams(): List<TeamDTO> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}