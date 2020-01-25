package com.example.nbawiki.model.database.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nbawiki.model.presentation.News

@Entity(tableName = "news")
data class NewsDb(
  @PrimaryKey val newsId : Int,
  @ColumnInfo(name = "DATE") val date : String,
  @ColumnInfo(name = "HOME_TEAM") val homeTeam : String,
  @ColumnInfo(name = "ENEMY_TEAM") val enemyTeam : String,
  @ColumnInfo(name = "TEAM_ID") val teamId : Int

) {
}

fun List<NewsDb>.asPresentationModels() : List<News>{
  return this.map {
    News(
      team = it.homeTeam,
      ennemyTeam =  it.enemyTeam,
      date = it.date
    )
  }
}