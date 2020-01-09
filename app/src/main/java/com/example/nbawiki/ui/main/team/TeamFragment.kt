package com.example.nbawiki.ui.main.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.nbawiki.R
import com.example.nbawiki.databinding.TeamFragmentBinding

class TeamFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val teamName : String = arguments?.let {
           TeamFragmentArgs.fromBundle(it).teamName
        } ?: ""

        val binding = DataBindingUtil.inflate<TeamFragmentBinding>(inflater, R.layout.team_fragment, container, false)

        val viewModel = ViewModelProviders.of(this).get(TeamViewModel::class.java)
        viewModel.initializeTeamData(teamName)

        binding.team = viewModel.team.value
        return binding.root
    }
}
