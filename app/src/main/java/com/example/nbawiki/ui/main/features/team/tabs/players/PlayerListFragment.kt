package com.example.nbawiki.ui.main.features.team.tabs.players

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbawiki.MyApplication.Companion.teamRepository
import com.example.nbawiki.R
import com.example.nbawiki.databinding.MainFragmentBinding
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.ui.main.features.main.recycleview.OnItemClickListener
import com.example.nbawiki.ui.main.features.team.TeamFragmentDirections
import com.example.nbawiki.ui.main.features.team.TeamViewModel
import com.example.nbawiki.ui.main.util.BaseViewModelFactory

class PlayerListFragment : Fragment(), OnItemClickListener {
    lateinit var viewModel : TeamViewModel
    lateinit var binding : MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpRecyclerView()
    }

    private fun setUpViewModel(){
        viewModel = ViewModelProviders.of(this, BaseViewModelFactory { TeamViewModel(teamRepository) })
            .get(TeamViewModel::class.java)
    }

    private fun setUpRecyclerView() {
        val players = viewModel.team.value?.teamMembers ?: emptyList()

        binding.teamRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = PlayerListAdapter(players, this@PlayerListFragment, layoutInflater)
        }

        viewModel.team.observe(viewLifecycleOwner, Observer<Team> {
            val binding = binding.teamRecyclerView.adapter as PlayerListAdapter
            binding.update(it.teamMembers)
        })
    }

    override fun onItemClicked(id: Int) {
        val action = TeamFragmentDirections.actionTeamFragmentToPlayerFragment(id)
        view!!.findNavController().navigate(action)
    }
}