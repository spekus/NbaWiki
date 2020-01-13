package com.example.nbawiki.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.javafaker.Faker
import java.util.*

object Repository {

    private var _teams: MutableLiveData<List<Team>> = MutableLiveData(createTeams())

    val NbaTeams: LiveData<List<Team>>
        get() = _teams

    fun getTeams(): LiveData<List<Team>> {
        return NbaTeams
    }

    fun getTheTeam(teamName: String): LiveData<Team> {
        val theTeam: Team? = _teams.value?.first {
            it.teamName.equals(teamName)
        }
        return MutableLiveData<Team>(theTeam)
    }

    fun getThePlayer(playerId: Int): LiveData<Player> {
        return MutableLiveData<Player>(_teams.value!!.find { it.teamMembers.any { it.id == playerId } }!!.teamMembers.find { it.id == playerId })
    }

    private fun createTeams(): List<Team> {
        return listOf(
            Team(
                teamName = "Raising ARIZONA",
                teamDescription = UUID.randomUUID().toString(),
                news = listOf(
                    News("Raising Arizona", "Vampire's Kiss"),
                    News("Raising Arizona2", "Vampire's Kiss2")
                ),
                teamMembers = listOf(Player(1, "James"), Player(2, "Augustas"))
            ),
            createRandomTeam(),
            createRandomTeam(),
            createRandomTeam(),
            createRandomTeam()
        )
    }

    private fun createRandomTeam(): Team {
        val faker = Faker()
        return Team(
            teamName = faker.name().fullName(),
            teamDescription = faker.gameOfThrones().quote(),
            news = createNews()
        )
    }

    private fun createNews(): List<News> {
        val faker = Faker()
        return listOf(
            News(
                team =  faker.name().firstName(),
                ennemyTeam = faker.name().firstName()
            ),
            News(
                team =  faker.name().firstName(),
                ennemyTeam = faker.name().firstName()
            ),
            News(
                team =  faker.name().firstName(),
                ennemyTeam = faker.name().firstName()
            )
        )
    }

}