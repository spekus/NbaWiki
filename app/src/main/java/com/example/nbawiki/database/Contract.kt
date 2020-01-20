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

    object NewsEntry : BaseColumns {
        const val TABLE_NAME = "news"
        const val COLUMN_NAME_HOME_TEAM= "homeTeamName"
        const val COLUMN_NAME_ENEMY_TEAM = "enemyTeamName"
        const val COLUMN_NAME_DATE = "matchDate"
        const val COLUMN_NAME_TEAM_ID = "teamId"
    }

    object PlayerEntry : BaseColumns {
        const val TABLE_NAME = "player"
        const val COLUMN_NAME_EXTERNAL_PLAYER_ID = "playerId"
        const val COLUMN_NAME_TEAM_ID = "teamId"
        const val COLUMN_NAME = "playerName"
        const val COLUMN_NAME_SURENAME = "playerSureName"
        const val COLUMN_NAME_ICON_URL = "teamIconUrl"
        const val COLUMN_NAME_IMAGE_URL = "image"
        const val COLUMN_NAME_WEIGHT = "weight"
        const val COLUMN_NAME_HEIGHT = "height"
        const val COLUMN_NAME_DESCRIPTION = "description"
    }
}