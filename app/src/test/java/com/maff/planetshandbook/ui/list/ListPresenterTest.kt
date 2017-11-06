package com.maff.planetshandbook.ui.list

import com.maff.planetshandbook.data.FakeData
import com.maff.planetshandbook.data.Planet
import com.maff.planetshandbook.data.Repository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import org.mockito.Mockito
import java.util.*

/**
 * Created by maff on 11/4/2017.
 */
class ListPresenterTest {
    private val repo: Repository = Mockito.mock(Repository::class.java)
    private val view: ListContract.View = Mockito.mock(ListContract.View::class.java)

    private lateinit var presenter : ListPresenter

    @Before
    fun before() {
        presenter = ListPresenter(view, repo)
    }

    @Test
    fun start() {
        val planets: List<Planet> = FakeData.getPlanets()

        Mockito.`when`(repo.getAllPlanets()).thenReturn(FakeData.getPlanets())
        presenter.start()
        Mockito.verify(view).showPlanets(planets)
    }

    @Test
    fun planetClicked() {
        val planet = FakeData.getPlanet()

        presenter.planetClicked(planet)
        Mockito.verify(view).showPlanetDetails(planet)
    }

    @Test
    fun aboutBtnClicked() {
        presenter.aboutBtnClicked()
        Mockito.verify(view).showAboutPage()
    }

}