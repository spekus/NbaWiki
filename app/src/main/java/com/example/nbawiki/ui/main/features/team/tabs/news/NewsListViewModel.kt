package com.example.nbawiki.ui.main.features.team.tabs.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.nbawiki.model.database.db.NewsDb
import com.example.nbawiki.model.database.db.TeamDb
import com.example.nbawiki.model.database.db.asNewsListItem
import com.example.nbawiki.repositories.interfaces.api.TeamRepository
import com.example.nbawiki.ui.main.features.team.models.NewsListElement
import com.example.nbawiki.ui.main.features.teamslist.TeamCard
import com.example.nbawiki.ui.main.features.teamslist.asPresentationModel
import com.example.nbawiki.util.Status
import com.example.nbawiki.util.wrapWithNewStatusInstance
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class NewsListViewModel @Inject constructor(
    val teamRepo: TeamRepository,
    @Named("TeamId")
    private val teamId: Int
) : ViewModel() {

    private var _newsList: LiveData<List<NewsDb>> = liveData(Dispatchers.IO) {
        val teamsEntity = teamRepo.getNews(teamId)
        emitSource(teamsEntity)
    }

    var newsListElement: LiveData<List<NewsListElement>> = Transformations.map(_newsList) {
        it.asNewsListItem()
    }

}