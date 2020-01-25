package com.example.nbawiki.ui.main.features.teamslist.recycleview

import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.databinding.ItemTeamCardBinding
import com.example.nbawiki.ui.main.features.teamslist.TeamCard

class TeamViewHolder(val binding: ItemTeamCardBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(teamCard: TeamCard, itemClickListener: OnItemClickListener) {
        binding.team = teamCard
        binding.teamCard.setOnClickListener {
            itemClickListener.onItemClicked(teamCard.id)
        }
    }
}