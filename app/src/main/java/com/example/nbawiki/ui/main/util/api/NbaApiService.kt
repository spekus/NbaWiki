package com.example.nbawiki.ui.main.util.api

import JsonKB
import TeamsGenerated
import com.example.nbawiki.model.dto.TeamDTO
import org.json.JSONObject
import timber.log.Timber
import java.net.HttpURLConnection
import java.net.URL

class NbaApiService : ApiService {

    val baseUrl: String = "https://www.thesportsdb.com/api/v1/json/1/"

    override fun getATeams(id: String): TeamDTO {
        return TeamDTO("")
    }


    private fun makeCallForString(url: String): String {
        var response: String? = ""
        Thread {
            val connection = URL(url).openConnection() as HttpURLConnection
            response = connection.inputStream.bufferedReader().readText()
            Timber.e(response)
        }.start()
        return response ?: ""
    }

    override fun getAllteams(): List<TeamDTO> {
        val lookUpUrl = "lookup_all_teams.php/?id=4387"
        val teamsString = makeCallForString(baseUrl + lookUpUrl)
        val teams: String = getTeams(teamsString)

//        JSONObject()

//        makeCallForPlayers("2")
        return listOf(TeamDTO("2"))
    }

    private fun getTeams(teamsString: String): String {
        var teams : MutableList<TeamsGenerated> = mutableListOf<TeamsGenerated>()
        val jsonObject = JSONObject(teamsString)
//        val jsonArray = jsonObject.getJSONArray("teams")
//        for (i in 0 until jsonArray.length()) {
////            teams.add(createTeam(jsonArray.getJSONObject(i)))
//        }

        return ""
    }

    private fun createTeam(jsonObject: JSONObject): TeamsGenerated {
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

//
//    @Throws(IOException::class, UnsupportedEncodingException::class)
//    private fun readStream(stream: InputStream, maxReadSize: Int): String? {
//        val reader: Reader? = InputStreamReader(stream, "UTF-8")
//        val rawBuffer = CharArray(maxReadSize)
//        val buffer = StringBuffer()
//        var readSize: Int = reader?.read(rawBuffer) ?: -1
//        var maxReadBytes = maxReadSize
//        while (readSize != -1 && maxReadBytes > 0) {
//            if (readSize > maxReadBytes) {
//                readSize = maxReadBytes
//            }
//            buffer.append(rawBuffer, 0, readSize)
//            maxReadBytes -= readSize
//            readSize = reader?.read(rawBuffer) ?: -1
//        }
//        return buffer.toString()
//    }


}


//    private fun makeCall(s: String): TeamDTO {
//        var textas  =""
//        Thread {
//           val connection = URL("https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php/?id=4387").openConnection() as HttpURLConnection
//           val data = connection.inputStream.bufferedReader().readText()
//           Log.e("TeamRepository",data )
//            textas = connection.inputStream.bufferedReader().readText()
//       }.start()
//        Log.e("TeamRepository",textas + "!!!!" )
//        return TeamDTO("1")
//    }
//    private fun makeCallForPlayers(s: String): TeamDTO {
//        var textas : String?  =""
//        Thread {
//
//            val connection = URL("https://www.thesportsdb.com/api/v1/json/1/searchplayers.php?t=Atlanta%20Hawks").openConnection() as HttpURLConnection
//            textas = connection.inputStream.bufferedReader().readText()
//            Timber.e(textas)
//        }.start()
//        Log.e("TeamRepository",textas + "!!!!" )
//        return TeamDTO("1")
//    }