package com.example.nbawiki.ui.main.team.tabs.news

import android.icu.text.DateFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.R
import com.example.nbawiki.model.News
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class NewsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.news_line_item, parent, false)) {
    private var mYourTeam: TextView? = null
    private var mEnemyTeam: TextView? = null
    private var mDate: TextView? = null

    init {
        mYourTeam = itemView.findViewById(R.id.team_your_team)
        mEnemyTeam = itemView.findViewById(R.id.team_enemy_team)
        mDate = itemView.findViewById(R.id.TV_date)
    }

    fun bind(news: News) {
        mYourTeam?.text = news.team
        mEnemyTeam?.text = news.ennemyTeam
        mDate?.text = news.date
    }
}