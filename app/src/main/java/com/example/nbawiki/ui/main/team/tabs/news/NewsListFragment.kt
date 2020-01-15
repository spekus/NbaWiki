package com.example.nbawiki.ui.main.team.tabs.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbawiki.R
import com.example.nbawiki.ui.main.team.TeamFragmentArgs
import com.example.nbawiki.ui.main.team.TeamViewModel
import com.example.nbawiki.ui.main.util.BaseViewModelFactory
import com.example.nbawiki.ui.main.util.Constants
import com.example.nbawiki.ui.main.util.Constants.repository
import kotlinx.android.synthetic.main.main_fragment.*

class NewsListFragment : Fragment() {

    lateinit var viewModel: TeamViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var teamId: Int = 0
        arguments?.takeIf { it.containsKey(Constants.ID_OBJECT) }?.apply {
            teamId = getInt(Constants.ID_OBJECT)
        }


        viewModel = ViewModelProviders.of(this, BaseViewModelFactory { TeamViewModel(repository) })
            .get(TeamViewModel::class.java)
        viewModel.initializeTeamData(teamId)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val news = viewModel.team.value?.news ?: emptyList()
        team_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = NewsListAdapter(news)
        }
    }
}