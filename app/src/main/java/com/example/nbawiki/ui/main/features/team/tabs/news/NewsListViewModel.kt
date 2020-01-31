package com.example.nbawiki.ui.main.features.team.tabs.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.nbawiki.model.database.db.asNewsListItem
import com.example.nbawiki.repositories.interfaces.api.TeamRepository
import com.example.nbawiki.ui.main.features.team.models.NewsListElement
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class NewsListViewModel @Inject constructor(
    teamRepo: TeamRepository,
    @Named("TeamId")
    private val teamId: Int
) : ViewModel() {

    var newsListElement: LiveData<List<NewsListElement>> = Transformations.map(teamRepo.news) {
        it.asNewsListItem()
    }

}