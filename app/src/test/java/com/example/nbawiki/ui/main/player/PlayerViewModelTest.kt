package com.example.nbawiki.ui.main.player

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.model.Repository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class PlayerViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    val mockRepository = Mockito.mock(Repository::class.java)

    lateinit var viewModel: PlayerViewModel
    val platerId = 1

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        Mockito.`when`(mockRepository.updateThePlayer(1))
            .thenReturn(MutableLiveData<Player>(
                Player()
            ))
    }

    @Test
    fun initializePlayerData_validID_liveDataUpdatedWithPlayer() {

        viewModel = PlayerViewModel(mockRepository)
        viewModel.initializePlayerData(platerId)
        viewModel.player.observeForever {}

        assertNotNull(viewModel.player.value)
    }

    @Test
    fun initializePlayerData_validID_playerWithSameId() {

        Mockito.`when`(mockRepository.updateThePlayer(platerId))
            .thenReturn(MutableLiveData<Player>(
                Player(id = platerId)
            ))

        viewModel = PlayerViewModel(mockRepository)
        viewModel.initializePlayerData(platerId)
        viewModel.player.observeForever {}

        assertEquals(platerId, viewModel.player.value!!.id)
    }

    @Test
    fun initializePlayerData_validId_callRepoOnlyOnce() {
        viewModel = PlayerViewModel(mockRepository)
        viewModel.initializePlayerData(platerId)

        Mockito.verify(mockRepository, times(0)).updateTeams()
        Mockito.verify(mockRepository, times(0)).updateTheTeam( ArgumentMatchers.anyInt())
        Mockito.verify(mockRepository, times(1)).updateThePlayer( ArgumentMatchers.anyInt())
    }
}

