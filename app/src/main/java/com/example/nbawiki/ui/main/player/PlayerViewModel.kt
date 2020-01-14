package com.example.nbawiki.ui.main.player

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nbawiki.model.Player
import com.example.nbawiki.model.TeamRepository
import com.squareup.picasso.Picasso

class PlayerViewModel : ViewModel() {
    lateinit var player : LiveData<Player>

    fun initializePlayerData(id: Int) {
        player  =  TeamRepository.getThePlayer(id)
    }

    companion object {
        @JvmStatic
        @BindingAdapter("android:src")
        fun bindCurrency(view: ImageView, url : String) {
            Picasso
                .get()
                .load(url)
                .into(view)
        }
    }
}
