package com.example.nbawiki.ui.main.features.main

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
import com.example.nbawiki.R
import com.example.nbawiki.databinding.MainFragmentBinding
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.ui.main.features.main.recycleview.OnItemClickListener
import com.example.nbawiki.ui.main.features.main.recycleview.TeamListAdapter
import com.example.nbawiki.ui.main.util.BaseViewModelFactory
import com.example.nbawiki.ui.main.util.Constants.repository

class MainFragment : Fragment(), OnItemClickListener {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding : MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, BaseViewModelFactory { MainViewModel(repository) })
            .get(MainViewModel::class.java)

        setUpRecyclerViewAdaper()

        viewModel.teams.observe(viewLifecycleOwner, Observer<List<Team>> {
            val adapter = binding.teamRecyclerView.adapter as TeamListAdapter
            adapter.update(it)
        })
    }

    private fun setUpRecyclerViewAdaper(){
        val nbaTeams = viewModel.teams.value ?: emptyList()

        binding.teamRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TeamListAdapter(nbaTeams, this@MainFragment, layoutInflater)
        }
    }

    override fun onItemClicked(teamId: Int){
        val action = MainFragmentDirections.actionMainFragmentToTeamFragment(teamId)
        view!!.findNavController().navigate(action)
    }
}
