package com.example.nbawiki.ui.main.features.main.recycleview

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.databinding.TeamCardItemBinding
import com.example.nbawiki.model.Team
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

        Picasso
            .get()
            .setLoggingEnabled(true)

//        Picasso
//            .get()
//            .load("http://via.placeholder.com/300.png")
//            .placeholder(R.mipmap.ic_launcher_round)
//            .into(mIcon)


//        Glide.with(parent.context).load("http://via.placeholder.com/300.png").into(mIcon)
    }

    fun bind(team: Team, itemClickListener: OnItemClickListener) {

        binding
        mTitleView?.text = team.teamName
        mDescription?.text = team.teamDescription
        binding.teamCard.setOnClickListener {
            itemClickListener.onItemClicked(team.id)
        }
    }
}