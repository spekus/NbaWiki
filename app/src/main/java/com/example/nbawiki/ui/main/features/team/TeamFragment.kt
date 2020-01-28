package com.example.nbawiki.ui.main.features.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.nbawiki.MyApplication
import com.example.nbawiki.R
import com.example.nbawiki.dagger.CustomViewModelFactory
import com.example.nbawiki.databinding.FragmentTeamBinding
import com.example.nbawiki.ui.main.features.team.models.TeamDetails
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject

class TeamFragment : Fragment() {
    @Inject
    lateinit var daggerFactory: CustomViewModelFactory

    private lateinit var demoCollectionPagerAdapter: DemoCollectionPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var viewModel : TeamViewModel
    private lateinit var binding : FragmentTeamBinding
    private var teamId : Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_team,
            container,
            false
        )

        MyApplication.get().component.inject(this)

        setUpViewModel()

        binding.teamBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabLayout(view)
    }

    private fun setUpViewModel() {
        teamId = arguments?.let {
            TeamFragmentArgs.fromBundle(it).teamId
        } ?: 0

//        viewModel = ViewModelProviders.of(this, ViewModelFactory { TeamViewModel(teamRepository) })
//            .get(TeamViewModel::class.java)
        viewModel = ViewModelProviders.of(this, daggerFactory).get(TeamViewModel::class.java)

        viewModel.initializeTeamData(teamId)

        viewModel.team.observe(viewLifecycleOwner, Observer<TeamDetails> {
            binding.teamDetails = it
        })

        viewModel.didApiCallFail.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(context, "Api call went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setUpTabLayout(view: View) {
        demoCollectionPagerAdapter = DemoCollectionPagerAdapter(childFragmentManager, teamId)
        viewPager = view.findViewById(R.id.pager)

        viewPager.adapter = demoCollectionPagerAdapter

        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
    }
}



