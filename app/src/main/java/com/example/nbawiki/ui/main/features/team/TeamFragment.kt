package com.example.nbawiki.ui.main.features.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.nbawiki.R
import com.example.nbawiki.dagger.CustomViewModelFactory
import com.example.nbawiki.databinding.FragmentTeamBinding
import com.example.nbawiki.ui.main.features.team.models.TeamDetails
import com.google.android.material.tabs.TabLayout
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TeamFragment : DaggerFragment() {
    @Inject
    lateinit var daggerFactory: CustomViewModelFactory

    private val viewModel by lazy {
        ViewModelProviders.of(this, daggerFactory).get(TeamViewModel::class.java)
    }

    private lateinit var demoCollectionPagerAdapter: DemoCollectionPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var binding: FragmentTeamBinding

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
        setUpViewModelObservers()

        binding.teamBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabLayout(view)
    }

    private fun setUpViewModelObservers() {
        viewModel.team.observe(viewLifecycleOwner, Observer<TeamDetails> {
            binding.teamDetails = it
        })

        viewModel.didApiCallFail.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(context, "Api call went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setUpTabLayout(view: View) {
        demoCollectionPagerAdapter = DemoCollectionPagerAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.pager)

        viewPager.adapter = demoCollectionPagerAdapter

        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
    }
}



