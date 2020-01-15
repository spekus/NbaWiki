package com.example.nbawiki.ui.main.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.nbawiki.R
import com.example.nbawiki.databinding.PlayerFragmentBinding
import com.example.nbawiki.ui.main.util.BaseViewModelFactory
import com.example.nbawiki.ui.main.util.Constants.repository

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

        viewModel = ViewModelProviders.of(this, BaseViewModelFactory { PlayerViewModel(repository) })
            .get(PlayerViewModel::class.java)
        viewModel.initializePlayerData(playerId)

        binding.player = viewModel.player.value

        binding.playerBackButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root

    }
}
