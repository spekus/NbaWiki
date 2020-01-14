package com.example.nbawiki.ui.main.features.main.recycleview

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.databinding.TeamCardItemBinding
import com.example.nbawiki.model.Team
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class TeamViewHolder(val binding: TeamCardItemBinding)
    : RecyclerView.ViewHolder(binding.root) {
    private var mTitleView: TextView? = null
    private var mDescription: TextView? = null
    private var mIcon : CircleImageView? = null

    init {
        mTitleView = binding.playerName
        mDescription =binding.TVDescription
        mIcon  =binding.CIVIcon



    }
    fun bind(team: Team, itemClickListener: OnItemClickListener) {
        setUpImage(team.imageUrl)
        mTitleView?.text = team.teamName
        mDescription?.text = team.teamDescription
        binding.teamCard.setOnClickListener {
            itemClickListener.onItemClicked(team.id)
        }
    }

    private fun setUpImage(image : String) {
        Picasso
            .get()
            .setLoggingEnabled(true)
//
//        Picasso.get()
//            .invalidate(url)

        Picasso
            .get()
            .load(image)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
            .into(mIcon)
    }
}