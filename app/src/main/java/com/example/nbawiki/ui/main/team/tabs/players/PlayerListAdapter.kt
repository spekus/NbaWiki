package com.example.nbawiki.ui.main.team.tabs.players

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.ui.main.features.main.recycleview.OnItemClickListener

class PlayerListAdapter (private val list: List<Player>, val itemClickListener : OnItemClickListener) : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlayerViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player: Player = list[position]
        holder.bind(player,itemClickListener)
    }

    override fun getItemCount(): Int = list.size
}