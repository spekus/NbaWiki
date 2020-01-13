package com.example.nbawiki.ui.main.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.nbawiki.R
import com.example.nbawiki.databinding.TeamFragmentBinding
import com.google.android.material.tabs.TabLayout

class TeamFragment : Fragment() {

    private lateinit var demoCollectionPagerAdapter: DemoCollectionPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var viewModel : TeamViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val teamId: Int = arguments?.let {
            TeamFragmentArgs.fromBundle(it).teamId
        } ?: 0

        val binding = DataBindingUtil.inflate<TeamFragmentBinding>(
            inflater,
            R.layout.team_fragment,
            container,
            false
        )

        viewModel = ViewModelProviders.of(this).get(TeamViewModel::class.java)
        viewModel.initializeTeamData(teamId)

        binding.team = viewModel.team.value


        binding.teamBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        demoCollectionPagerAdapter = DemoCollectionPagerAdapter(childFragmentManager, viewModel.team.value?.id ?:0 )
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = demoCollectionPagerAdapter

        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
    }
}



