package com.example.nbawiki.ui.main.features.team.tabs.players

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.R
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.ui.main.features.main.recycleview.OnItemClickListener
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class PlayerViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.player_line_item, parent, false)) {
    private var mName: TextView? = null
    private var mImage : CircleImageView? = null

    init {
        mName = itemView.findViewById(R.id.player_name)
        mImage = itemView.findViewById(R.id.circleImageView)
    }

    fun bind(player: Player, itemClickListener: OnItemClickListener) {
        setUpImage(player.imageUrl)
        mName?.text = player.name
        itemView.findViewById<ConstraintLayout>(R.id.player_line_card).setOnClickListener {
            itemClickListener.onItemClicked(player.id)
        }
    }

    private fun setUpImage(image : String) {
            val formattedString = formatImageUrl(image)

            Picasso
                .get()
                .load(formattedString)
                .into(mImage)
    }

    private fun formatImageUrl(image : String?): String? {
        // Picasso can not handle blank url string, it can handle null
        if (image?.trim()?.isBlank() != false){
            return null
        }
        return image
    }

}