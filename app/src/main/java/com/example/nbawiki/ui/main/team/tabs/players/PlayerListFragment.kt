package com.example.nbawiki.ui.main.team.tabs.players

import android.os.Bundle
import android.util.Log
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
import com.example.nbawiki.databinding.PlayerLineItemBinding
import com.example.nbawiki.model.Team
import com.example.nbawiki.ui.main.features.main.MainFragmentDirections
import com.example.nbawiki.ui.main.features.main.recycleview.OnItemClickListener
import com.example.nbawiki.ui.main.team.TeamFragment
import com.example.nbawiki.ui.main.team.TeamFragmentDirections
import com.example.nbawiki.ui.main.team.TeamViewModel
import com.example.nbawiki.ui.main.util.Constants.ID_OBJECT
import kotlinx.android.synthetic.main.main_fragment.*

class PlayerListFragment : Fragment(),
    OnItemClickListener {

    lateinit var viewModel : TeamViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var teamName = ""
        arguments?.takeIf { it.containsKey(ID_OBJECT) }?.apply {
            teamName = getString(ID_OBJECT).toString()
        }


        viewModel = ViewModelProviders.of(this)
            .get(TeamViewModel::class.java)
        viewModel.initializeTeamData(teamName)
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

    override fun onTeamClicked(team: Team) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemClicked(id: Int) {
        val action = TeamFragmentDirections.actionTeamFragmentToPlayerFragment(id)
        view!!.findNavController().navigate(action)
    }
}