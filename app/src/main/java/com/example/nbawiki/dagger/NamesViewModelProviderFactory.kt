package com.example.nbawiki.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nbawiki.repositories.PlayerRepo
import com.example.nbawiki.ui.main.features.player.PlayerViewModel

class NamesViewModelProviderFactory(
    private val repo: PlayerRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = PlayerViewModel(repo) as T
}
