package com.example.nbawiki.ui.main.features.teamslist.recycleview

import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.databinding.TeamCardItemBinding
import com.example.nbawiki.model.presentation.Team

class TeamViewHolder(val binding: TeamCardItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(team: Team, itemClickListener: OnItemClickListener) {
        binding.team = team
        binding.teamCard.setOnClickListener {
            itemClickListener.onItemClicked(team.id)
        }
    }
}