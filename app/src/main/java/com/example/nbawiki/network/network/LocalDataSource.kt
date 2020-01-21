package com.example.nbawiki.network.network

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.nbawiki.database.DataBaseContract
import com.example.nbawiki.database.FeedReaderDbHelper
import com.example.nbawiki.model.presentation.News
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.model.presentation.Team

class LocalDataSource(val context: Context) {
    private var db: SQLiteDatabase
    private var dbRead: SQLiteDatabase

    init {
        val dbHelper = FeedReaderDbHelper(context)
        db = dbHelper.writableDatabase
        dbRead = dbHelper.readableDatabase
    }


    fun putTeam(team: Team) {
        val values = ContentValues().apply {
            put(DataBaseContract.TeamEntry.COLUMN_NAME_TITLE, team.teamName)
            put(DataBaseContract.TeamEntry.COLUMN_NAME_DESCRIPTION, team.teamDescription)
            put(DataBaseContract.TeamEntry.COLUMN_NAME_EXTERNAL_ID, team.id)
            put(DataBaseContract.TeamEntry.COLUMN_NAME_ICON_URL, team.teamIconUrl)
            put(DataBaseContract.TeamEntry.COLUMN_NAME_IMAGE_URL, team.imageUrl)
        }

        val newRowId = db?.insert(DataBaseContract.TeamEntry.TABLE_NAME, null, values)
    }

    fun putPlayer(player: Player, teamID: Int) {
        val values = ContentValues().apply {
            put(DataBaseContract.PlayerEntry.COLUMN_NAME_DESCRIPTION, player.description)
            put(DataBaseContract.PlayerEntry.COLUMN_NAME_SURENAME, player.sureName)
            put(DataBaseContract.PlayerEntry.COLUMN_NAME_EXTERNAL_PLAYER_ID, player.id)
            put(DataBaseContract.PlayerEntry.COLUMN_NAME_HEIGHT, player.height)
            put(DataBaseContract.PlayerEntry.COLUMN_NAME_NAME, player.name)
            put(DataBaseContract.PlayerEntry.COLUMN_NAME_IMAGE_URL, player.imageUrl)
            put(DataBaseContract.PlayerEntry.COLUMN_NAME_TEAM_ID, teamID)
            put(DataBaseContract.PlayerEntry.COLUMN_NAME_WEIGHT, player.weight)
        }
        val newRowId = db?.insert(DataBaseContract.PlayerEntry.TABLE_NAME, null, values)
    }

    fun putNews(news: News, teamID: Int) {
        val values = ContentValues().apply {
            put(DataBaseContract.NewsEntry.COLUMN_NAME_DATE, news.date)
            put(DataBaseContract.NewsEntry.COLUMN_NAME_ENEMY_TEAM, news.ennemyTeam)
            put(DataBaseContract.NewsEntry.COLUMN_NAME_HOME_TEAM, news.team)
            put(DataBaseContract.NewsEntry.COLUMN_NAME_TEAM_ID, teamID)
        }
        val newRowId = db?.insert(DataBaseContract.NewsEntry.TABLE_NAME, null, values)
    }

    fun getAllTeams(): List<Team> {
        val projection = arrayOf(
            DataBaseContract.TeamEntry.COLUMN_NAME_TITLE,
            DataBaseContract.TeamEntry.COLUMN_NAME_DESCRIPTION,
            DataBaseContract.TeamEntry.COLUMN_NAME_EXTERNAL_ID,
            DataBaseContract.TeamEntry.COLUMN_NAME_ICON_URL,
            DataBaseContract.TeamEntry.COLUMN_NAME_IMAGE_URL
        )

        // Filter results WHERE "title" = 'My Title'
        val selection = "${DataBaseContract.TeamEntry.COLUMN_NAME_TITLE} = ?"
        val selectionArgs = arrayOf("*")

        val cursor = db.query(
            DataBaseContract.TeamEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val team = mutableListOf<Team>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val title = cursor.getString(0)
                val description = cursor.getString(1)
                val externalId = cursor.getInt(2)
                val iconUrl = cursor.getString(3)
                val imageUrl = cursor.getString(4)

                val news: List<News> = getNews(externalId)

                team.add(
                    Team(
                        id = externalId,
                        teamIconUrl = iconUrl,
                        teamDescription = description,
                        teamName = title,
                        imageUrl = imageUrl,
                        news = news
                    )
                )
                cursor.moveToNext()
            }


        }


        return team
    }

    private fun getNews(teamID: Int): List<News> {
        val projection = arrayOf(
            DataBaseContract.NewsEntry.COLUMN_NAME_TEAM_ID,
            DataBaseContract.NewsEntry.COLUMN_NAME_HOME_TEAM,
            DataBaseContract.NewsEntry.COLUMN_NAME_ENEMY_TEAM,
            DataBaseContract.NewsEntry.COLUMN_NAME_DATE
        )

        val selection = "${DataBaseContract.NewsEntry.COLUMN_NAME_TEAM_ID} = ?"
        val selectionArgs = arrayOf("$teamID")

        val cursor = db.query(
            DataBaseContract.NewsEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val news = mutableListOf<News>()

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val teamID = cursor.getString(0)
                val team = cursor.getString(1)
                val ennemyTeam = cursor.getString(2)
                val date = cursor.getString(3)
                news.add(
                    News(
                        team = team,
                        ennemyTeam = ennemyTeam,
                        date = date
                    )
                )
                cursor.moveToNext()
            }
        }
        return news
    }




}