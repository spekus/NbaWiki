package com.example.nbawiki.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.nbawiki.model.database.dao.PlayerDao
import com.example.nbawiki.model.database.db.PlayerDb
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
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class PlayerRepoTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    val mockDatabase = mock(PlayerDao::class.java)
    val repo = PlayerRepo(mockDatabase)

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var mockitoRule = MockitoJUnit.rule()


    val mockPlayer = PlayerDb(12)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)

        `when`(mockDatabase.getThePlayer(1))
            .thenReturn(
                mockPlayer
            )
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }


    @Test
    fun refreshThePlayer__liveDataUpdatedWithExpectedPlayer() = runBlockingTest {
        `when`(mockDatabase.getThePlayer(2))
            .thenReturn(PlayerDb(2))
        repo.selectedPlayer.observeForever {  }
        repo.refreshThePlayer(2)
        assertEquals(repo.selectedPlayer.value , PlayerDb(2))
    }

    @Test
    fun refreshThePlayer__invokeDataBaseOnce() = runBlockingTest {

        repo.refreshThePlayer(ArgumentMatchers.anyInt())

        verify(mockDatabase, times(0)).getAll()
        verify(mockDatabase, times(0)).insertAll()
        verify(mockDatabase, times(0)).getPlayersByTeam(ArgumentMatchers.anyInt())
        verify(mockDatabase, times(1)).getThePlayer(ArgumentMatchers.anyInt())
    }

    @Test
    fun refreshThePlayer__liveDataUpdatedOnlyOnce() = runBlockingTest {
        val mockObserver  = mock<Observer<PlayerDb>>()

        repo.selectedPlayer.observeForever(mockObserver)
        repo.refreshThePlayer(ArgumentMatchers.anyInt())

        verify(mockObserver , times(1)).onChanged(any())
    }
}