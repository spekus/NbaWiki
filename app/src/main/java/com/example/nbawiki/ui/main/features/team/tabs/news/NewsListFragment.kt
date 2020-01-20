package com.example.nbawiki.ui.main.features.team.tabs.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbawiki.R
import com.example.nbawiki.databinding.MainFragmentBinding
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.ui.main.features.team.TeamViewModel
import com.example.nbawiki.ui.main.util.BaseViewModelFactory
import com.example.nbawiki.ui.main.util.Constants
import com.example.nbawiki.ui.main.util.Constants.repository
import kotlinx.android.synthetic.main.main_fragment.*

class NewsListFragment : Fragment() {
    lateinit var viewModel: TeamViewModel
    lateinit var binding: MainFragmentBinding

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
        setUpRecyclerViewAdapter()
    }

    private fun setUpViewModel() {
        var teamId: Int = 0
        arguments?.takeIf { it.containsKey(Constants.ID_OBJECT) }?.apply {
            teamId = getInt(Constants.ID_OBJECT)
        }

        viewModel = ViewModelProviders.of(this, BaseViewModelFactory { TeamViewModel(repository) })
            .get(TeamViewModel::class.java)

        viewModel.initializeTeamData(teamId)
    }

    private fun setUpRecyclerViewAdapter() {
        binding.teamRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = NewsListAdapter(viewModel.team.value?.news ?: listOf(), layoutInflater)
        }

        viewModel.team.observe(viewLifecycleOwner, Observer<Team> {
            val adapter = team_recycler_view.adapter as NewsListAdapter
            adapter.update(it.news)
        })
    }
}