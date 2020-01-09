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
import com.example.nbawiki.model.Team

class TeamFragment : Fragment() {

    lateinit var teamName : String
    var team : Team = Team("sadsdfsdf", "dafsdfsdf")

    companion object {
        fun newInstance() = TeamFragment()
    }

    private lateinit var viewModel: TeamViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.let {
           teamName = TeamFragmentArgs.fromBundle(it).teamName
        }

        val binding = DataBindingUtil.inflate<TeamFragmentBinding>(inflater, R.layout.team_fragment, container, false)
        binding.team = team
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TeamViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
