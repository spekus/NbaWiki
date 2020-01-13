package com.example.nbawiki.ui.main.team.tabs.players

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbawiki.R
import com.example.nbawiki.databinding.MainFragmentBinding
import com.example.nbawiki.model.Team
import com.example.nbawiki.ui.main.features.main.recycleview.OnItemClickListener
import com.example.nbawiki.ui.main.team.TeamFragmentDirections
import com.example.nbawiki.ui.main.team.TeamViewModel
import com.example.nbawiki.ui.main.util.Constants.ID_OBJECT

class PlayerListFragment : Fragment(),
    OnItemClickListener {

    lateinit var viewModel : TeamViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var teamId = 0
        arguments?.takeIf { it.containsKey(ID_OBJECT) }?.apply {
            teamId = getInt(ID_OBJECT)
        }


        viewModel = ViewModelProviders.of(this)
            .get(TeamViewModel::class.java)
        viewModel.initializeTeamData(teamId)
        val binding = DataBindingUtil.inflate<MainFragmentBinding>(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )

        val players = viewModel.team.value?.teamMembers ?: emptyList()
        binding.teamRecycleView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = PlayerListAdapter(players, this@PlayerListFragment)
        }

        return binding.root
    }

    override fun onItemClicked(id: Int) {
        val action = TeamFragmentDirections.actionTeamFragmentToPlayerFragment(id)
        view!!.findNavController().navigate(action)
    }
}