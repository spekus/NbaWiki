package com.example.nbawiki.ui.main.features.teamslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbawiki.MyApplication
import com.example.nbawiki.R
import com.example.nbawiki.dagger.CustomViewModelFactory
import com.example.nbawiki.databinding.FragmentListBinding
import com.example.nbawiki.ui.main.features.teamslist.recycleview.OnItemClickListener
import com.example.nbawiki.ui.main.features.teamslist.recycleview.TeamListAdapter
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment : DaggerFragment(), OnItemClickListener {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding : FragmentListBinding

    @Inject
    lateinit var daggerFactory: CustomViewModelFactory

//    override fun onAttach(context: Context) {
//        AndroidSupportInjection.inject(this)
//        super.onAttach(context)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list,
            container,
            false
        )

//        MyApplication.get().component.inject(this)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, daggerFactory).get(MainViewModel::class.java)

        viewModel.didApiCallFail.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(context, "Api call went wrong", Toast.LENGTH_LONG).show()
            }
        })
        setUpRecyclerViewAdaper()
    }

    private fun setUpRecyclerViewAdaper(){
        val nbaTeams = viewModel.teams.value ?: emptyList()

        binding.teamRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TeamListAdapter(nbaTeams, this@MainFragment, layoutInflater)
        }

        viewModel.teams.observe(viewLifecycleOwner, Observer<List<TeamCard>> {
            val adapter = binding.teamRecyclerView.adapter as TeamListAdapter
            adapter.update(it)
        })
    }

    override fun onItemClicked(teamId: Int){
        val action = MainFragmentDirections.actionMainFragmentToTeamFragment(teamId)
        view!!.findNavController().navigate(action)
    }
}
