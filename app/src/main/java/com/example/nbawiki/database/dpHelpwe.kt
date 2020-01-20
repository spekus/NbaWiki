package com.example.nbawiki.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.nbawiki.database.commands.SQL_CREATE_ENTRIES
import com.example.nbawiki.database.commands.SQL_CREATE_PLAYERS
import com.example.nbawiki.database.commands.SQL_CREATE_TEAMS

object commands {
    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${Contract.TeamEntry.TABLE_NAME}"
    const val SQL_DELETE_ENTRIES2 = "DROP TABLE IF EXISTS ${Contract.NewsEntry.TABLE_NAME}"
    const val SQL_DELETE_ENTRIES3 = "DROP TABLE IF EXISTS ${Contract.PlayerEntry.TABLE_NAME}"

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${Contract.TeamEntry.TABLE_NAME} (" +
                "${Contract.TeamEntry.COLUMN_NAME_TITLE} TEXT," +
                "${Contract.TeamEntry.COLUMN_NAME_ICON_URL} TEXT," +
                "${Contract.TeamEntry.COLUMN_NAME_IMAGE_URL} TEXT," +
                "${Contract.TeamEntry.COLUMN_NAME_EXTERNAL_ID} INTEGER PRIMARY KEY," +
                "${Contract.TeamEntry.COLUMN_NAME_DESCRIPTION} TEXT)"

    const val SQL_CREATE_TEAMS =
        "CREATE TABLE ${Contract.NewsEntry.TABLE_NAME} (" +
                "${Contract.NewsEntry.COLUMN_NAME_DATE} TEXT," +
                "${Contract.NewsEntry.COLUMN_NAME_ENEMY_TEAM} TEXT," +
                "${Contract.NewsEntry.COLUMN_NAME_HOME_TEAM} TEXT," +
                "${Contract.NewsEntry.COLUMN_NAME_TEAM_ID} INTEGER," +
                "${BaseColumns._ID} INTEGER PRIMARY KEY)"

    const val SQL_CREATE_PLAYERS  =
        "CREATE TABLE ${Contract.PlayerEntry.TABLE_NAME} (" +
                "${Contract.PlayerEntry.COLUMN_NAME_DESCRIPTION} TEXT," +
                "${Contract.PlayerEntry.COLUMN_NAME_EXTERNAL_PLAYER_ID} INTEGER PRIMARY KEY," +
                "${Contract.PlayerEntry.COLUMN_NAME_HEIGHT} TEXT," +
                "${Contract.PlayerEntry.COLUMN_NAME_IMAGE_URL} TEXT," +
                "${Contract.PlayerEntry.COLUMN_NAME_SURENAME} TEXT," +
                "${Contract.PlayerEntry.COLUMN_NAME_NAME} TEXT," +
                "${Contract.PlayerEntry.COLUMN_NAME_WEIGHT} TEXT," +
                "${Contract.PlayerEntry.COLUMN_NAME_TEAM_ID} INTEGER)"
}


class FeedReaderDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
        db.execSQL(SQL_CREATE_TEAMS)
        db.execSQL(SQL_CREATE_PLAYERS)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(commands.SQL_DELETE_ENTRIES)
        db.execSQL(commands.SQL_DELETE_ENTRIES2)
        db.execSQL(commands.SQL_DELETE_ENTRIES3)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        const val DATABASE_VERSION = 9
        const val DATABASE_NAME = "FeedReader.db"
    }

}