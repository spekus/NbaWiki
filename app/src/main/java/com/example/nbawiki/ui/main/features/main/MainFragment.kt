package com.example.nbawiki.ui.main.features.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.nbawiki.R
import com.example.nbawiki.model.Team
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private val nbaTeams = listOf(
        Team("Raising Arizona" ),
        Team("Vampire's Kiss"),
        Team("Con Air"),
        Team("Gone in 60 Seconds"),
        Team("National Treasure"),
        Team("The Wicker Man"),
        Team("Ghost Rider"),
        Team("Knowing")
    )

    companion object {
        fun newInstance() =
            MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        team_recycle_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(nbaTeams)
        }
        main_fragment_button.setOnClickListener{
            view.findNavController().navigate(R.id.action_mainFragment_to_teamFragment)
        }
    }
}
