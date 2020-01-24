package com.example.nbawiki.ui.main.features.team.tabs.players

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.R
import com.example.nbawiki.databinding.ItemPlayerLineBinding
import com.example.nbawiki.ui.main.features.teamslist.recycleview.OnItemClickListener

class PlayerViewHolder(val binding : ItemPlayerLineBinding) :
    RecyclerView.ViewHolder(binding.root){

    fun bind(player: PlayerListElement, itemClickListener: OnItemClickListener) {
        binding.player = player
        itemView.findViewById<ConstraintLayout>(R.id.player_line_card).setOnClickListener {
            itemClickListener.onItemClicked(player.id)
        }
    }
}