package com.example.nbawiki.ui.main.features.teamslist.recycleview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.databinding.ItemTeamCardBinding
import com.example.nbawiki.model.presentation.Team

class TeamListAdapter(private var list: List<Team>, val itemClickListener : OnItemClickListener, private val layoutInflater: LayoutInflater) : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val cardItemView = ItemTeamCardBinding.inflate(layoutInflater)
        return TeamViewHolder(
            cardItemView
        )
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team: Team = list[position]
        holder.bind(team,itemClickListener)
    }

    override fun getItemCount(): Int = list.size

    fun update(newList : List<Team>){
        this.list = newList
        this.notifyDataSetChanged()
    }
}