package com.example.nbawiki.ui.main.features.team.tabs.players

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.databinding.ItemPlayerLineBinding
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.ui.main.features.teamslist.recycleview.OnItemClickListener

class PlayerListAdapter(
    private var list: List<Player>,
    val itemClickListener: OnItemClickListener,
    private val layoutInflater: LayoutInflater
) : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            ItemPlayerLineBinding.inflate(layoutInflater)
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player: Player = list[position]
        holder.bind(player, itemClickListener)
    }

    override fun getItemCount(): Int = list.size
    fun update(players: List<Player>?) {
        list = players ?: emptyList()
        this.notifyDataSetChanged()
    }
}