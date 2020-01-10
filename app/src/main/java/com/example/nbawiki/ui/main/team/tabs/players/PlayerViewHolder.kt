package com.example.nbawiki.ui.main.team.tabs.players

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.R
import com.example.nbawiki.model.Player
import com.example.nbawiki.model.Team
import com.example.nbawiki.ui.main.features.main.recycleview.OnItemClickListener

class PlayerViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.player_line_item, parent, false)) {
    private var mName: TextView? = null

    init {
        mName = itemView.findViewById(R.id.player_name)
    }

    fun bind(player: Player, itemClickListener: OnItemClickListener) {
        mName?.text = player.name
//        itemView.findViewById<CardView>(R.id.team_card).setOnClickListener {
//            itemClickListener.onTeamClicked(team)
//        }
    }
}