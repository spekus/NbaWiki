package com.example.nbawiki.ui.main.features.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.R
import com.example.nbawiki.model.Team

class TeamViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.team_card_item, parent, false)) {
    private var mTitleView: TextView? = null
    private var mDescriptionView: TextView? = null


    init {
        mTitleView = itemView.findViewById(R.id.team_title)
        mDescriptionView = itemView.findViewById(R.id.team_description)
    }

    fun bind(team: Team) {
        mTitleView?.text = team.teamName
        mDescriptionView?.text = team.teamDescription
    }

}