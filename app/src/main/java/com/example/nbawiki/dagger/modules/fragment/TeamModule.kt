package com.example.nbawiki.dagger.modules.fragment

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.nbawiki.dagger.ViewModelKey
import com.example.nbawiki.ui.main.features.team.TeamFragment
import com.example.nbawiki.ui.main.features.team.TeamFragmentArgs
import com.example.nbawiki.ui.main.features.team.TeamViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
abstract class TeamModule {
    @Binds
    @IntoMap
    @ViewModelKey(TeamViewModel::class)
    abstract fun bindTeamViewModel(customViewModel: TeamViewModel): ViewModel


    @Module
    companion object {
        @Named("TeamId")
        @Provides
        @JvmStatic
        fun getId(fragment: TeamFragment): Int =
            TeamFragmentArgs.fromBundle(fragment.requireArguments()).teamId
    }
}
