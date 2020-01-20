package com.example.nbawiki.network.network

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.nbawiki.database.Contract
import com.example.nbawiki.database.FeedReaderDbHelper
import com.example.nbawiki.model.presentation.News
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.model.presentation.Team

class LocalDataSource(val context: Context) {
    lateinit var db : SQLiteDatabase

    init {
        val dbHelper = FeedReaderDbHelper(context)
        db = dbHelper.writableDatabase
    }


    fun putTeam(team : Team) {
        val values = ContentValues().apply {
            put(Contract.TeamEntry.COLUMN_NAME_TITLE, team.teamName)
            put(Contract.TeamEntry.COLUMN_NAME_DESCRIPTION, team.teamDescription)
            put(Contract.TeamEntry.COLUMN_NAME_EXTERNAL_ID, team.id)
            put(Contract.TeamEntry.COLUMN_NAME_ICON_URL, team.teamIconUrl)
            put(Contract.TeamEntry.COLUMN_NAME_IMAGE_URL, team.imageUrl)
        }

        val newRowId = db?.insert(Contract.TeamEntry.TABLE_NAME, null, values)
    }

    fun putPlayer(player: Player, teamID: Int) {
        val values = ContentValues().apply {
            put(Contract.PlayerEntry.COLUMN_NAME_DESCRIPTION, player.description)
            put(Contract.PlayerEntry.COLUMN_NAME_SURENAME, player.sureName)
            put(Contract.PlayerEntry.COLUMN_NAME_EXTERNAL_PLAYER_ID, player.id)
            put(Contract.PlayerEntry.COLUMN_NAME_HEIGHT, player.height)
            put(Contract.PlayerEntry.COLUMN_NAME_NAME, player.name)
            put(Contract.PlayerEntry.COLUMN_NAME_IMAGE_URL, player.imageUrl)
            put(Contract.PlayerEntry.COLUMN_NAME_TEAM_ID, teamID)
            put(Contract.PlayerEntry.COLUMN_NAME_WEIGHT, player.weight)
        }
        val newRowId = db?.insert(Contract.PlayerEntry.TABLE_NAME, null, values)
    }

    fun putNews(news: News, teamID: Int) {
        val values = ContentValues().apply {
            put(Contract.NewsEntry.COLUMN_NAME_DATE, news.date)
            put(Contract.NewsEntry.COLUMN_NAME_ENEMY_TEAM, news.ennemyTeam)
            put(Contract.NewsEntry.COLUMN_NAME_HOME_TEAM, news.team)
            put(Contract.NewsEntry.COLUMN_NAME_TEAM_ID, teamID)
        }
        val newRowId = db?.insert(Contract.NewsEntry.TABLE_NAME, null, values)
    }


}
