package com.example.nbawiki.ui.main.player

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nbawiki.model.Player
import com.example.nbawiki.model.Repository
import com.example.nbawiki.model.TeamRepository
import com.squareup.picasso.Picasso

class PlayerViewModel(val repositry : Repository) : ViewModel() {
    lateinit var player : LiveData<Player>

    fun initializePlayerData(id: Int) {
        player  =  repositry.getThePlayer(id)
    }


}
