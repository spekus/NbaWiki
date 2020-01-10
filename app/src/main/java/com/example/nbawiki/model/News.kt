package com.example.nbawiki.model

import java.util.*

class News(
    val team: String = "NAN",
    val ennemyTeam: String = "NAN",
    val date: Date = Calendar.getInstance().time
)
