package com.example.nbawiki.ui.main.features.team

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.nbawiki.ui.main.features.team.tabs.news.NewsListFragment
import com.example.nbawiki.ui.main.features.team.tabs.players.PlayerListFragment
import com.example.nbawiki.util.Constants

class DemoCollectionPagerAdapter(fm: FragmentManager, val teamId: Int) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int = 2
    override fun getItem(tabNumber: Int): Fragment {
        val fragment = when (tabNumber) {
            0 -> NewsListFragment()
            else -> PlayerListFragment()
        }

        fragment.arguments = Bundle().apply {
            putInt(Constants.ID_OBJECT, teamId)
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position){
            0 -> "NEWS"
            else -> "PLAYERS"
        }
    }
}