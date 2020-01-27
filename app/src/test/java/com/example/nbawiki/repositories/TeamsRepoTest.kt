package com.example.nbawiki.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nbawiki.datasource.retrofit.WebService
import com.example.nbawiki.model.database.dao.TeamsDao
import com.example.nbawiki.model.database.db.TeamDb
import com.example.nbawiki.model.dto.teams.TeamDTO
import com.example.nbawiki.model.dto.teams.TeamsDTO
import com.example.nbawiki.util.TimePreferenceWizard
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.test.assertTrue

class TeamsRepoTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val mockDatabase = Mockito.mock(TeamsDao::class.java)
    private val mockApi = Mockito.mock(WebService::class.java)
    private val mockWizard = Mockito.mock(TimePreferenceWizard::class.java)
    private val ApiResponse = mock<Response<TeamsDTO>>()
    private val repo = TeamsRepo(mockApi, mockWizard, mockDatabase)

    val mockObserver  = mock<Observer<in List<TeamDb?>>>()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var mockitoRule = MockitoJUnit.rule()

    val teamsDto = TeamsDTO(listOf(TeamDTO()))

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun getDidApiCallFail() {
    }

    @Test
    fun getAllTeams_itIsTimeToUpdate_UpdateLiveDataTwice() = runBlockingTest {
        Mockito.`when`(mockApi.getAllTeams(anyString())).thenReturn(
            Response.success(200,  teamsDto)
        )
        Mockito.`when`(mockWizard.isItTimeToUpdate(anyString(), anyLong())).thenReturn(true)

        repo.allTeams.observeForever(mockObserver)

        repo.getTeams()

        Mockito.verify(mockObserver, times(2)).onChanged(any())
    }

    @Test
    fun getAllTeams_itIsNotTimeToUpdate_UpdateLiveDataOnce() = runBlockingTest {
        Mockito.`when`(mockApi.getAllTeams(anyString())).thenReturn(
            Response.success(200,  teamsDto)
        )
        Mockito.`when`(mockWizard.isItTimeToUpdate(anyString(), anyLong())).thenReturn(false)

        repo.allTeams.observeForever(mockObserver)

        repo.getTeams()

        Mockito.verify(mockObserver, times(1)).onChanged(any())
    }


    @Test
    fun getAllTeams_ApiCallFailed_UpdateLiveDataOnce() = runBlockingTest {
        Mockito.`when`(ApiResponse.isSuccessful).thenReturn(false)
        Mockito.`when`(mockApi.getAllTeams(anyString())).thenReturn(ApiResponse)
        Mockito.`when`(mockWizard.isItTimeToUpdate(anyString(), anyLong())).thenReturn(true)

        repo.allTeams.observeForever(mockObserver)

        repo.getTeams()

        Mockito.verify(mockObserver, times(1)).onChanged(any())
    }

    @Test
    fun getAllTeams_apiCalUnsuccessful_updateApiCallResult() = runBlockingTest {
        Mockito.`when`(ApiResponse.isSuccessful).thenReturn(false)
        Mockito.`when`(mockApi.getAllTeams(anyString())).thenReturn(ApiResponse)
        Mockito.`when`(mockWizard.isItTimeToUpdate(anyString(), anyLong())).thenReturn(true)

        repo.didApiCallFail.observeForever {}

        repo.getTeams()

        assertTrue { repo.didApiCallFail.value!!.peekContent() }
    }

    @Test
    fun getAllTeams_apiException_updateApiCallResultWithFailure() = runBlockingTest {
        Mockito.`when`(mockApi.getAllTeams(anyString())).thenThrow(RuntimeException())
        Mockito.`when`(mockWizard.isItTimeToUpdate(anyString(), anyLong())).thenReturn(true)

        repo.didApiCallFail.observeForever {}

        repo.getTeams()

        assertTrue { repo.didApiCallFail.value!!.peekContent() }
    }
}