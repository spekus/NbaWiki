package com.example.nbawiki.espresso

import android.app.Application
import android.widget.Button
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.nbawiki.MainActivity
import com.example.nbawiki.R
import com.example.nbawiki.model.TeamRepository
import com.example.nbawiki.ui.main.features.main.recycleview.TeamViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EspressoTests {


    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun onTeamClick__ValidTeamNameDisplayed() {
        onView(withId(R.id.team_recycle_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<TeamViewHolder>(1, click()))

        val teamName = TeamRepository.getTheTeam(2).value!!.teamName

        onView(withId(R.id.teamName))
            .check(matches(isDisplayed()))
            .check(matches(withText(teamName)))
    }

}