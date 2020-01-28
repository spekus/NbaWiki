package com.example.nbawiki.ui.main.features.team.tabs.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.nbawiki.model.database.db.asNewsListItem
import com.example.nbawiki.repositories.interfaces.api.TeamRepository
import com.example.nbawiki.ui.main.features.team.models.NewsListElement
import javax.inject.Inject

class NewsListViewModel @Inject constructor(teamRepo: TeamRepository) : ViewModel() {

    var newsListElement : LiveData<List<NewsListElement>> = Transformations.map(teamRepo.news) {
        it.asNewsListItem()
    }

}