package com.example.nbawiki.ui.main.player

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.model.Player
import com.example.nbawiki.model.Repository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import kotlin.test.assertNotNull


class PlayerViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    val mockRepository = Mockito.mock(Repository::class.java)


    lateinit var viewModel: PlayerViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.viewModel = PlayerViewModel()
        Mockito
//            .`when`(mockRepository.getThePlayer(any()))
            .`when`(mockRepository.getThePlayer(1))
            .thenReturn (MutableLiveData<Player>())
    }

    @Test
    fun doTesting(){
        Mockito
//            .`when`(mockRepository.getThePlayer(any()))
            .`when`(mockRepository.getThePlayer(1))
            .thenReturn (MutableLiveData<Player>())

        val viewModel = PlayerViewModel()
        viewModel.initializePlayerData(1)
        viewModel.player.observeForever{}

        assertNotNull(viewModel.player.value)
    }


    @Test
    fun getPlayer() {
    }

    @Test
    fun setPlayer() {
    }

    @Test
    fun initializePlayerData() {
    }

}