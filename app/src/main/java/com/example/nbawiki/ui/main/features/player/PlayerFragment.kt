package com.example.nbawiki.ui.main.features.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.nbawiki.MyApplication.Companion.playerRepository
import com.example.nbawiki.MyApplication.Companion.teamRepository
import com.example.nbawiki.R
import com.example.nbawiki.databinding.PlayerFragmentBinding
import com.example.nbawiki.ui.main.util.BaseViewModelFactory

class PlayerFragment : Fragment() {
    private lateinit var viewModel: PlayerViewModel
    private lateinit var binding: PlayerFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.player_fragment,
            container,
            false
        )

        setUpViewModel()
        setUpBinding()

        return binding.root
    }

    private fun setUpViewModel() {
        val playerId: Int = arguments?.let {
            PlayerFragmentArgs.fromBundle(it).playerId
        } ?: 0

        viewModel = ViewModelProviders.of(this, BaseViewModelFactory {
            PlayerViewModel(playerRepository)
        }).get(PlayerViewModel::class.java)

        viewModel.initializePlayerData(playerId)

    }

    private fun setUpBinding() {
        viewModel.player.observe(viewLifecycleOwner, Observer {
            binding.player = it
        })

        binding.playerBackButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
