package com.example.nbawiki.repositories.interfaces.api

import androidx.lifecycle.LiveData
import com.example.nbawiki.model.database.db.TeamDb
import com.example.nbawiki.util.Resource

interface TeamListRepository  {
    suspend fun getTeamsWithResponse(): LiveData<Resource<List<TeamDb?>>>
}