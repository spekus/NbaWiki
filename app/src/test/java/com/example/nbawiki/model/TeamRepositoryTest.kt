package com.example.nbawiki.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.ui.main.util.Constants.repository
import kotlinx.coroutines.*
import kotlinx.coroutines.test.setMain
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import kotlin.test.assertFailsWith
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description


class TeamRepositoryTest {

    private val validTeamId: Int = 1
    private val validTeamId2: Int = 2
    private val inValidId: Int = -1

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    //TEAM TESTS:


    @Before
    fun initializeRepo() {
        val mainThreadSurrogate = newSingleThreadContext("UI thread")
        Dispatchers.setMain(mainThreadSurrogate)

    }

    @Test
    fun getTeams__returnsNotEmptyList() =  runBlocking<Unit>{
        var teamList : List<Team>? = null

        GlobalScope.launch (Dispatchers.Unconfined) {
            repository.refreshTeams()
            repository.nbaTeams.observeForever { }
            repository.refreshTeams()
            val smth = repository.nbaTeams.value
//            teamList = repository.nbaTeams.value
//            assertTrue(teamList!!.isNotEmpty())
            assertTrue(repository.nbaTeams.value!!.isNotEmpty())
        }

//        assertTrue(repository.nbaTeams.value!!.isEmpty())
    }

//    @Test
//    fun getTheTeam_validId_ReturnsNotNull() {
//        val team = TeamRepository.getTheTeam(validTeamId).value
//        assertNotNull(team)
//    }
//
//    @Test
//    fun getTheTeam_validId_ReturnsTeamWithThatId() {
//        val returnedId = TeamRepository.getTheTeam(validTeamId).value!!.id
//        assertEquals(validTeamId, returnedId)
//    }
//
//    @Test
//    fun getTheTeamTwice_sameId_ReturnsSameTeams() {
//        val firstTeam = TeamRepository.getTheTeam(validTeamId).value!!
//        val secondTeam = TeamRepository.getTheTeam(validTeamId).value!!
//        assertEquals(firstTeam, secondTeam)
//    }
//
//    @Test
//    fun getTheTeamTwice_differentId_ReturnsDifferentTeams() {
//        val firstTeam = TeamRepository.getTheTeam(validTeamId).value!!
//        val secondTeam = TeamRepository.getTheTeam(validTeamId2).value!!
//        assertNotEquals(firstTeam, secondTeam)
//    }
//
//    @Test
//    fun getTheTeam_InvalidId_failsWithException() {
//        assertFailsWith<NoSuchElementException> { TeamRepository.getTheTeam(inValidId).value }
//    }
//
//    //PLAYER TESTS
//
//    private val validPlayerId = 101
//    private val validPlayerId2 = 102
//
//    @Test
//    fun getThePlayer_validId_returnsAPlayer() {
//        val team = TeamRepository.getThePlayer(validPlayerId).value
//        assertNotNull(team)
//    }
//
//    @Test
//    fun getThePlayer_validId_returnsPlayerWithSameId() {
//        val actualPlayerID = TeamRepository.getThePlayer(validPlayerId).value!!.id
//        assertEquals(validPlayerId, actualPlayerID)
//    }
//
//    @Test
//    fun getThePlayerTwice_sameId_returnsSameTwoPlayer() {
//        val actualPlayer1 = TeamRepository.getThePlayer(validPlayerId).value!!
//        val actualPlayer2 = TeamRepository.getThePlayer(validPlayerId).value!!
//        assertEquals(actualPlayer1, actualPlayer2)
//    }
//
//    @Test
//    fun getThePlayerTwice_differentId_returnsTwoDifferentPlayers() {
//        val actualPlayer1 = TeamRepository.getThePlayer(validPlayerId).value!!
//        val actualPlayer2 = TeamRepository.getThePlayer(validPlayerId2).value!!
//        assertNotEquals(actualPlayer1, actualPlayer2)
//    }
//
//    @Test
//    fun getThePlayer_inValidId_failsWithException() {
//        assertFailsWith<KotlinNullPointerException> { TeamRepository.getThePlayer(inValidId) }
//    }

}

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}


@ExperimentalCoroutinesApi
class CoroutineTestRule(val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) : TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}