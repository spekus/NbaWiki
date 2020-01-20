package com.example.nbawiki.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.nbawiki.database.commands.SQL_CREATE_ENTRIES

object commands {
    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${Contract.TeamEntry.TABLE_NAME}"

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${Contract.TeamEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${Contract.TeamEntry.COLUMN_NAME_TITLE} TEXT," +
                "${Contract.TeamEntry.COLUMN_NAME_SUBTITLE} TEXT)"
}


class FeedReaderDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(commands.SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "FeedReader.db"
    }

}