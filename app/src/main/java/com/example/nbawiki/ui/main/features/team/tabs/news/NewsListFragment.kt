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
import com.example.nbawiki.MyApplication.Companion.teamRepository
import com.example.nbawiki.R
import com.example.nbawiki.databinding.FragmentListBinding
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.ui.main.features.team.TeamViewModel
import com.example.nbawiki.ui.main.util.BaseViewModelFactory
import kotlinx.android.synthetic.main.fragment_list.*

class NewsListFragment : Fragment() {
    lateinit var viewModel: TeamViewModel
    lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list,
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
        viewModel = ViewModelProviders.of(this, BaseViewModelFactory { TeamViewModel(teamRepository) })
            .get(TeamViewModel::class.java)
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