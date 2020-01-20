package com.example.nbawiki.ui.main.features.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.nbawiki.R
import com.example.nbawiki.databinding.PlayerFragmentBinding
import com.example.nbawiki.ui.main.util.BaseViewModelFactory
import com.example.nbawiki.ui.main.util.Constants.repository

class PlayerFragment : Fragment() {
    private lateinit var viewModel: PlayerViewModel
    private lateinit var binding: PlayerFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val playerId: Int = arguments?.let {
            PlayerFragmentArgs.fromBundle(it).playerId
        } ?: 0

        binding = DataBindingUtil.inflate<PlayerFragmentBinding>(
            inflater,
            R.layout.player_fragment,
            container,
            false
        )

        binding.playerBackButton.setOnClickListener {
            findNavController().navigateUp()
        }

        setUpViewModel(playerId)

        return binding.root
    }

    private fun setUpViewModel(playerId: Int) {
        viewModel = ViewModelProviders.of(this, BaseViewModelFactory {
            PlayerViewModel(repository)
        })
            .get(PlayerViewModel::class.java)

        viewModel.initializePlayerData(playerId)

        viewModel.player.observe(viewLifecycleOwner, Observer {
            binding.player = viewModel.player.value
        })
    }
}
