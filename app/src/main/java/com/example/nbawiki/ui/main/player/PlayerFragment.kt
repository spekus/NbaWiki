package com.example.nbawiki.ui.main.player

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

import com.example.nbawiki.R
import com.example.nbawiki.databinding.PlayerFragmentBinding
import com.example.nbawiki.databinding.TeamFragmentBinding
import com.example.nbawiki.ui.main.team.TeamFragmentArgs

class PlayerFragment : Fragment() {

    companion object {
        fun newInstance() = PlayerFragment()
    }

    private lateinit var viewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<PlayerFragmentBinding>(
            inflater,
            R.layout.player_fragment,
            container,
            false
        )

        val playerId: Int = arguments?.let {
            PlayerFragmentArgs.fromBundle(it).playerId
        } ?: 0

        viewModel = ViewModelProviders.of(this).get(PlayerViewModel::class.java)
        viewModel.initializePlayerData(playerId)

        binding.player = viewModel.player.value

        binding.playerBackButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
