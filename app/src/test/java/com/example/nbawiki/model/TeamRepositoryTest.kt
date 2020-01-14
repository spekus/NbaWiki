package com.example.nbawiki.model

import org.junit.Test

import org.junit.Assert.*

class TeamRepositoryTest {

    private val validTeamId : Int =2

    @Test
    fun getTeams__returnsNotEmptyList() {
        val teamList  =  TeamRepository.getTeams().value!!
        assertFalse(teamList.isEmpty())
    }

    @Test
    fun getTheTeam_validId_ReturnsNotNull() {
        val team = TeamRepository.getTheTeam(validTeamId).value
        assertNotNull(team)
    }

    @Test
    fun getTheTeam_validId_ReturnsTeamWithThatId() {
        val returnedId = TeamRepository.getTheTeam(validTeamId).value!!.id
        assertEquals(validTeamId, returnedId)
    }

    @Test
    fun getTheTeamTwice_sameId_ReturnsSameTeams() {
        val firstTeam = TeamRepository.getTheTeam(validTeamId).value!!
        val secondTeam = TeamRepository.getTheTeam(validTeamId).value!!
        assertEquals(firstTeam, secondTeam)
    }

    @Test
    fun getTheTeamTwice_differentId_ReturnsDifferentTeams() {
        val firstTeam = TeamRepository.getTheTeam(validTeamId).value!!
//        val secondTeam = TeamRepository.getTheTeam(validTeamId).value!!
//        assertEquals(firstTeam, secondTeam)
    }

    @Test
    fun getTheTeam_InvalidId_() {
    }

    @Test
    fun getThePlayer_validId_returnsPlayerWithThatID() {
    }
}