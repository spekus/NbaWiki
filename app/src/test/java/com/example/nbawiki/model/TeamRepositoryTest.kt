package com.example.nbawiki.model

import com.example.nbawiki.ui.main.util.Constants.repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import kotlin.test.assertFailsWith

class TeamRepositoryTest {

    private val validTeamId: Int = 1
    private val validTeamId2: Int = 2
    private val inValidId: Int = -1

    //TEAM TESTS:


    @Before
    fun initializeRepo() {
        val mainThreadSurrogate = newSingleThreadContext("UI thread")
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun getTeams__returnsNotEmptyList() {

            repository.
            val teamList = repository.nbaTeams.value
            assertFalse(teamList!!.isEmpty())
        }

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

//}