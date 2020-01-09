package com.example.nbawiki.ui.main.features.main.recycleview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.R
import com.example.nbawiki.model.Team


class TeamListAdapter(private val list: List<Team>, val itemClickListener : OnItemClickListener) : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TeamViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team: Team = list[position]
        holder.bind(team,itemClickListener)
    }

    override fun getItemCount(): Int = list.size
}