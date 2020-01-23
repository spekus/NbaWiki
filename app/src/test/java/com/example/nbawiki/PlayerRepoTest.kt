package com.example.nbawiki

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.database.LocalDataSource
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.network.network.PlayerRepo
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
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit

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

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }


    @Test
    fun getSelectedPlayer() {
    }

    @Test
    fun refreshThePlayer__onlyInvokedOnce() = runBlockingTest {

        Mockito.`when`(mockDatabase.getThePlayer(1))
            .thenReturn(
                MutableLiveData<Player>(Player(12))
            )

        repo.refreshThePlayer(1)

        Mockito.verify(mockDatabase, times(0)).getAllTeams()
        Mockito.verify(mockDatabase, times(0)).getTheTeam(1)
        Mockito.verify(mockDatabase, times(1)).getThePlayer(1)
    }
}