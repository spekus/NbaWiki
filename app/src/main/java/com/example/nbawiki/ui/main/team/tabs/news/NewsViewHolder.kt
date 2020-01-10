package com.example.nbawiki.ui.main.team.tabs.news

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.R
import com.example.nbawiki.model.News

class NewsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.news_line_item, parent, false)) {
    private var mYourTeam: TextView? = null
    private var mEnemyTeam: TextView? = null

    init {
        mYourTeam = itemView.findViewById(R.id.team_your_team)
        mEnemyTeam = itemView.findViewById(R.id.team_enemy_team)
    }

    fun bind(news: News) {
        mYourTeam?.text = news.team
        mEnemyTeam?.text = news.ennemyTeam
    }
}