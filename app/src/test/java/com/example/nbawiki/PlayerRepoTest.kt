package com.example.nbawiki

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.nbawiki.database.LocalDataSource
import com.example.nbawiki.ui.main.features.player.Player
import com.example.nbawiki.network.network.PlayerRepo
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class PlayerRepoTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    val mockDatabase = Mockito.mock(LocalDataSource::class.java)
    val repo = PlayerRepo(mockDatabase)

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var mockitoRule = MockitoJUnit.rule()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)

        Mockito.`when`(mockDatabase.getThePlayer(1))
            .thenReturn(
                MutableLiveData<Player>(mockPlayer)
            )

    }

    val mockPlayer = Player(12)

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }


    @Test
    fun refreshThePlayer__liveDataUpdatedWithExpectedPlayer() = runBlockingTest {
        repo.selectedPlayer.observeForever {  }
        repo.refreshThePlayer(1)
        assertEquals(repo.selectedPlayer.value , mockPlayer)
    }

    @Test
    fun refreshThePlayer__onlyInvokedOnce() = runBlockingTest {

        repo.refreshThePlayer(1)

        Mockito.verify(mockDatabase, times(0)).getAllTeams()
        Mockito.verify(mockDatabase, times(0)).getTheTeam(1)
        Mockito.verify(mockDatabase, times(1)).getThePlayer(1)
    }

    @Test
    fun refreshThePlayer__liveDataUpdatedOnlyOnece() = runBlockingTest {
        val mockObserver  = mock<Observer<Player>>()

        repo.selectedPlayer.observeForever(mockObserver)
        repo.refreshThePlayer(1)

        verify(mockObserver , times(2)).onChanged(any())
    }
}