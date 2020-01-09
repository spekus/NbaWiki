package com.example.nbawiki.ui.main.features.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbawiki.R
import com.example.nbawiki.model.Team
import com.example.nbawiki.ui.main.features.main.recycleview.OnItemClickListener
import com.example.nbawiki.ui.main.features.main.recycleview.TeamListAdapter
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*

class MainFragment : Fragment(), OnItemClickListener {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val nbaTeams = viewModel.teams.value ?: emptyList()
        team_recycle_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TeamListAdapter(nbaTeams, this@MainFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val nbaTeams = viewModel.teams.value ?: emptyList()
//        team_recycle_view.apply {
//            layoutManager = LinearLayoutManager(activity)
//            adapter = TeamListAdapter(nbaTeams, this@MainFragment)
//        }
    }

    override fun onTeamClicked(team: Team) {
        Log.e("MainFragment", "${team.teamName}")
        view!!.findNavController().navigate(R.id.action_mainFragment_to_teamFragment)
    }
}
