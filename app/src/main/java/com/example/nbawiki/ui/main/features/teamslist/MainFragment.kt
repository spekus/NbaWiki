package com.example.nbawiki.ui.main.features.teamslist

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
import com.example.nbawiki.R
import com.example.nbawiki.dagger.CustomViewModelFactory
import com.example.nbawiki.databinding.FragmentListBinding
import com.example.nbawiki.ui.main.features.teamslist.recycleview.OnItemClickListener
import com.example.nbawiki.ui.main.features.teamslist.recycleview.TeamListAdapter
import com.example.nbawiki.util.Status
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_team.*
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
        setUpRecyclerViewAdaper()

        return binding.root
    }

    private fun setUpRecyclerViewAdaper(){

        binding.teamRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TeamListAdapter(emptyList(), this@MainFragment, layoutInflater)
        }

        viewModel.teams.observe(viewLifecycleOwner, Observer<Status<List<TeamCard>?>> {
            val adapter = binding.teamRecyclerView.adapter as TeamListAdapter
            when(it){
                is Status.Success ->{
                    hideShiver()
                    adapter.update(it.data ?: emptyList())
                }
                is Status.CachedData ->{
                    hideShiver()
                    adapter.update(it.data ?: emptyList())
                }
                is Status.Loading ->  {
                    showShiver()
                }
                is Status.Error -> Toast.makeText(context, "Api call went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onItemClicked(teamId: Int){
        val action = MainFragmentDirections.actionMainFragmentToTeamFragment(teamId)
        view!!.findNavController().navigate(action)
    }


}


fun Fragment.hideShiver(){
    shimmer_view_container.visibility = View.GONE
    shimmer_view_container.stopShimmerAnimation()
}


fun Fragment.showShiver(){
    shimmer_view_container.visibility = View.VISIBLE
    shimmer_view_container.startShimmerAnimation()
}


