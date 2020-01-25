package com.example.nbawiki.model.database.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nbawiki.model.presentation.Team

@Entity
data class TeamDb(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "TEAM_NAME") val teamName: String?,
    @ColumnInfo(name = "ICON_URL") val iconUrl: String?,
    @ColumnInfo(name = "IMAGE_URL") val imageUrl: String?,
    @ColumnInfo(name = "DESCRIPTION") val descriptor: String?
)

fun TeamDb.asPresentationModel(): Team {
    return Team(
        id = this.id,
        teamName = this.teamName ?: "",
        teamDescription = this.descriptor ?: "",
        teamIconUrl = this.iconUrl ?: "",
        teamMembers = emptyList(),
        news = emptyList(),
        imageUrl = this.imageUrl ?: ""
    )
}