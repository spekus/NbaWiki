package com.example.nbawiki.model.database.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TeamDb(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "TEAM_NAME") val teamName: String?,
    @ColumnInfo(name = "ICON_URL") val iconUrl: String?,
    @ColumnInfo(name = "IMAGE_URL") val imageUrl: String?,
    @ColumnInfo(name = "DESCRIPTION") val descriptor: String?
)