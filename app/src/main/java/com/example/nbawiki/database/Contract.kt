package com.example.nbawiki.database

import android.provider.BaseColumns

object Contract {
    object TeamEntry : BaseColumns {
        const val TABLE_NAME = "team"
        const val COLUMN_NAME_EXTERNAL_ID = "teamExternalId"
        const val COLUMN_NAME_TITLE = "teamName"
        const val COLUMN_NAME_DESCRIPTION = "teamDescription"
        const val COLUMN_NAME_ICON_URL = "teamIconUrl"
        const val COLUMN_NAME_IMAGE_URL = "imageUrl"
    }

}