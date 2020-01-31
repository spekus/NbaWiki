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
import com.example.nbawiki.model.database.db.TeamDb
import com.example.nbawiki.ui.main.features.teamslist.recycleview.OnItemClickListener
import com.example.nbawiki.ui.main.features.teamslist.recycleview.TeamListAdapter
import com.example.nbawiki.util.Resource
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment : DaggerFragment(), OnItemClickListener {
    private lateinit var binding : FragmentListBinding

    @Inject
    lateinit var daggerFactory: CustomViewModelFactory

    val viewModel  by lazy { ViewModelProviders.of(this, daggerFactory).get(MainViewModel::class.java)}

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
        setUpRecyclerViewAdaper()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        viewModel = ViewModelProviders.of(this, daggerFactory).get(MainViewModel::class.java)
//
//        viewModel.didApiCallFail.observe(viewLifecycleOwner, Observer {
//            it.getContentIfNotHandled()?.let {
//                Toast.makeText(context, "Api call went wrong", Toast.LENGTH_LONG).show()
//            }
//        })
//        setUpRecyclerViewAdaper()
    }

    private fun setUpRecyclerViewAdaper(){
//        val nbaTeams = viewModel.teams.value ?: emptyList()

        binding.teamRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TeamListAdapter(emptyList(), this@MainFragment, layoutInflater)
        }

        viewModel.teams.observe(viewLifecycleOwner, Observer< Resource< out List<TeamCard>?>> {
            val adapter = binding.teamRecyclerView.adapter as TeamListAdapter
            when(it){
                is Resource.Success -> adapter.update(it.data ?: emptyList())
                is Resource.Loading -> {
                    if (it.data.isNullOrEmpty()) {  }  // inform user of loading
                    else { adapter.update(it.data) }
                }
                is Resource.Error -> Toast.makeText(context, "Api call went wrong", Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onItemClicked(teamId: Int){
        val action = MainFragmentDirections.actionMainFragmentToTeamFragment(teamId)
        view!!.findNavController().navigate(action)
    }
}
