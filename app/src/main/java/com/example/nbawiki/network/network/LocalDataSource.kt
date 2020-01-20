package com.example.nbawiki.network.network

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.nbawiki.database.Contract
import com.example.nbawiki.database.FeedReaderDbHelper
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


}
