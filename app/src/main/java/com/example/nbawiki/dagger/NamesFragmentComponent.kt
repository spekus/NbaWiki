package com.example.nbawiki.dagger

import com.example.nbawiki.ui.main.features.player.PlayerFragment
import dagger.Component

@Component(modules = [NamesFragmentModule::class, NetworkModule::class])
//    , dependencies = [PocketTreasureComponent::class])
interface NamesFragmentComponent {
    fun inject(playerFragment: PlayerFragment)
}