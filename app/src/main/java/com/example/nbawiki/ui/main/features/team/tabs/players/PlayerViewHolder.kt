package com.example.nbawiki.ui.main.features.team.tabs.players

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.R
import com.example.nbawiki.databinding.PlayerLineItemBinding
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.ui.main.features.main.recycleview.OnItemClickListener

class PlayerViewHolder(val binding : PlayerLineItemBinding) :
    RecyclerView.ViewHolder(binding.root){

    fun bind(player: Player, itemClickListener: OnItemClickListener) {
        binding.player = player
        itemView.findViewById<ConstraintLayout>(R.id.player_line_card).setOnClickListener {
            itemClickListener.onItemClicked(player.id)
        }
    }
}