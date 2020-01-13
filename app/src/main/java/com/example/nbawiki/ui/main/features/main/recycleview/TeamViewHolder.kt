package com.example.nbawiki.ui.main.features.main.recycleview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nbawiki.R
import com.example.nbawiki.model.Team
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class TeamViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.team_card_item, parent, false)) {
    private var mTitleView: TextView? = null
    private var mDescription: TextView? = null

    init {
        mTitleView = itemView.findViewById(R.id.player_name)
        mDescription =itemView.findViewById(R.id.TV_description)
        val mIcon : CircleImageView =itemView.findViewById(R.id.CIV_icon)

        Picasso
            .get()
            .setLoggingEnabled(true)

        Picasso
            .get()
            .load("http://via.placeholder.com/300.png")
            .placeholder(R.mipmap.ic_launcher_round)
            .into(mIcon)


//        Glide.with(parent.context).load("http://via.placeholder.com/300.png").into(mIcon)
    }

    fun bind(team: Team, itemClickListener: OnItemClickListener) {


        mTitleView?.text = team.teamName
        mDescription?.text = team.teamDescription
        itemView.findViewById<CardView>(R.id.team_card).setOnClickListener {
            itemClickListener.onItemClicked(team.id)
        }
    }
}