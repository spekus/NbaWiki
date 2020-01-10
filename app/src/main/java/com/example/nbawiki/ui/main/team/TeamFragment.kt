package com.example.nbawiki.ui.main.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.nbawiki.R
import com.example.nbawiki.databinding.TeamFragmentBinding
import com.example.nbawiki.model.Team
import com.example.nbawiki.ui.main.features.main.recycleview.OnItemClickListener
import com.example.nbawiki.ui.main.team.tabs.players.PlayerListAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.main_fragment.*

class TeamFragment : Fragment() {

    private lateinit var demoCollectionPagerAdapter: DemoCollectionPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var viewModel : TeamViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val teamName: String = arguments?.let {
            TeamFragmentArgs.fromBundle(it).teamName
        } ?: ""

        val binding = DataBindingUtil.inflate<TeamFragmentBinding>(
            inflater,
            R.layout.team_fragment,
            container,
            false
        )

        viewModel = ViewModelProviders.of(this).get(TeamViewModel::class.java)
        viewModel.initializeTeamData(teamName)

        binding.team = viewModel.team.value

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        demoCollectionPagerAdapter = DemoCollectionPagerAdapter(childFragmentManager, viewModel.team.value?.teamName ?:"" )
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = demoCollectionPagerAdapter
//        TeamFragmentDirections.actionTeamFragmentToNewsListFragment(viewModel.team.value!!.teamName)

        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
    }
}





class DemoCollectionPagerAdapter(fm: FragmentManager,val teamName: String) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = 2

    override fun getItem(tabNumber: Int): Fragment {
        val fragment = when (tabNumber) {
            0 -> NewsListFragment()
            else -> PlayerListFragment()
        }


        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putInt(ARG_OBJECT, tabNumber + 1)
            putString(ID_OBJECT, teamName)

        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "OBJECT ${(position + 1)}"
    }
}

private const val ARG_OBJECT = "object"
private const val ID_OBJECT = "teamID"

class NewsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_collection_object, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
//            val textView = view.findViewById<TextView>(R.id.tab_text)
//            textView.text = getInt(ARG_OBJECT).toString()
        }

    }
}

class PlayerListFragment : Fragment(), OnItemClickListener {

    lateinit var viewModel : TeamViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var teamName : String = ""
        arguments?.takeIf { it.containsKey(ID_OBJECT) }?.apply {
            //            val textView = view.findViewById<TextView>(R.id.tab_text)
            teamName = getString(ID_OBJECT).toString()
        }


        viewModel = ViewModelProviders.of(this).get(TeamViewModel::class.java)
        viewModel.initializeTeamData(teamName)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val players = viewModel.team.value?.teamMembers ?: emptyList()
        team_recycle_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = PlayerListAdapter(players, this@PlayerListFragment)
        }


//        team_recycle_view.apply {
//            layoutManager = LinearLayoutManager(activity)
//            adapter = TeamListAdapter(nbaTeams, this@MainFragment)
//        }

//        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
//            val textView = view.findViewById<TextView>(R.id.tab_text)
//            textView.text = getInt(ARG_OBJECT).toString()
//        }

    }

    override fun onTeamClicked(team: Team) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


