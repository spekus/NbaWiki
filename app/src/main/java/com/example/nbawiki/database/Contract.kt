package com.example.nbawiki.database

import android.provider.BaseColumns

object Contract {
    object TeamEntry : BaseColumns {
        const val TABLE_NAME = "entry"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_SUBTITLE = "subtitle"
    }


}