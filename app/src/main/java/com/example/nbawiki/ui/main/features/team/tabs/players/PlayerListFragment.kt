package com.example.nbawiki.ui.main.features.team.tabs.players

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
import com.example.nbawiki.ui.main.features.team.TeamFragmentDirections
import com.example.nbawiki.ui.main.features.team.models.PlayerListElement
import com.example.nbawiki.ui.main.features.teamslist.hideShiver
import com.example.nbawiki.ui.main.features.teamslist.recycleview.OnItemClickListener
import com.example.nbawiki.ui.main.features.teamslist.showShiver
import com.example.nbawiki.util.Status
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PlayerListFragment : DaggerFragment(), OnItemClickListener {
    @Inject
    lateinit var daggerFactory: CustomViewModelFactory

    private val viewModel by lazy{ ViewModelProviders.of(this, daggerFactory).get(PlayerListViewModel::class.java) }

    lateinit var binding : FragmentListBinding

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
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {

        binding.teamRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = PlayerListAdapter(emptyList(), this@PlayerListFragment, layoutInflater)
        }

        viewModel.players.observe(viewLifecycleOwner, Observer<Status<List<PlayerListElement>?>> {
            val binding = binding.teamRecyclerView.adapter as PlayerListAdapter
            if(it is Status.Success || it is Status.CachedData){
                hideShiver()
                binding.update(it.data)
            }
            if(it is Status.Error){
                Toast.makeText(context, "Api call went wrong", Toast.LENGTH_LONG).show()
            }
            if(it is Status.Loading){
                showShiver()
            }
        })

    }

    override fun onItemClicked(id: Int) {
        val action = TeamFragmentDirections.actionTeamFragmentToPlayerFragment(id)
        view!!.findNavController().navigate(action)
    }
}