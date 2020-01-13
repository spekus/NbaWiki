package com.example.nbawiki.model

import com.github.javafaker.Faker

object DataCreator {

    val faker = Faker()
    var id: Int = 0

    fun createTeams(): List<Team> {
        return listOf(
            createRandomTeam(),
            createRandomTeam(),
            createRandomTeam(),
            createRandomTeam()
        )
    }

    private fun createRandomTeam(): Team {
        id = id.inc()
        return Team(
            id = id + 1,
            teamName = faker.name().fullName(),
            teamDescription = faker.gameOfThrones().quote(),
            news = createNews(),
            teamMembers = createTeamMembers()
        )
    }

    private fun createTeamMembers(): List<Player> {
        return listOf(
            createPlayer(),
            createPlayer(),
            createPlayer(),
            createPlayer()
        )
    }

    private fun createPlayer(): Player {
        id = id.inc()
        return Player(
            id = id,
            name = faker.name().firstName(),
            sureName = faker.name().lastName(),
            description = faker.chuckNorris().fact()
        )
    }

    private fun createNews(): List<News> {
        return listOf(
            createNewsItem(),
            createNewsItem(),
            createNewsItem(),
            createNewsItem()
        )
    }

    private fun createNewsItem(): News {
        return News(
            team = faker.name().firstName(),
            ennemyTeam = faker.name().firstName()
        )
    }
}