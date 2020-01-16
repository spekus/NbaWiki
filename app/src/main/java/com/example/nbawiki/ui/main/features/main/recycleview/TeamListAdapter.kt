package com.example.nbawiki.ui.main.features.main.recycleview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.databinding.TeamCardItemBinding
import com.example.nbawiki.model.presentation.Team

class TeamListAdapter(private val list: List<Team>, val itemClickListener : OnItemClickListener, private val layoutInflater: LayoutInflater) : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
        val cardItemView = TeamCardItemBinding.inflate(layoutInflater)
        return TeamViewHolder(
            cardItemView
        )
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team: Team = list[position]
        holder.bind(team,itemClickListener)
    }

    override fun getItemCount(): Int = list.size
}