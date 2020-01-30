package com.example.nbawiki.ui.main.features.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.nbawiki.R
import com.example.nbawiki.dagger.CustomViewModelFactory
import com.example.nbawiki.databinding.FragmentPlayerBinding
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class PlayerFragment : DaggerFragment()  {
    @Inject
    lateinit var daggerFactory: CustomViewModelFactory

    @Inject
    lateinit var adress : String

    private val viewModel by lazy { ViewModelProviders
        .of(this, daggerFactory)[PlayerViewModel::class.java]
    }
    private lateinit var binding: FragmentPlayerBinding

    //        viewModel = ViewModelProviders.of(this, ViewModelFactory {
//            PlayerViewModel(playerRepository)
//        }).get(PlayerViewModel::class.java)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_player,
            container,
            false
        )

        setUpViewModel()
        setUpBinding()

        Timber.e(adress)

        return binding.root
    }

    private fun setUpViewModel() {
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
