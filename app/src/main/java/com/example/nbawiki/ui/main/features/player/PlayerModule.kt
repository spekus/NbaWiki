package com.example.nbawiki.ui.main.features.player

import androidx.lifecycle.ViewModel
import com.example.nbawiki.dagger.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
abstract class PlayerModule {
    @Binds
    @IntoMap
    @ViewModelKey(PlayerViewModel::class)
    abstract fun bindPlayerViewModel(customViewModel:  PlayerViewModel): ViewModel

    @Module
    companion object {
        @Named("Player Id123")
        @Provides
        @JvmStatic
        fun providePlayerId(fragment: PlayerFragment): Int =
            PlayerFragmentArgs.fromBundle(fragment.requireArguments()).playerId
    }
}