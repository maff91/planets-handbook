package com.maff.planetshandbook.ui.details

import com.maff.planetshandbook.data.*
import org.junit.Test

import org.junit.Before
import org.mockito.Mockito
import org.mockito.Mockito.*

/**
 * Created by maff on 11/5/2017.
 */
class DetailsPresenterTest {
    private val repo: Repository = Mockito.mock(Repository::class.java)
    private val view: DetailsContract.View = Mockito.mock(DetailsContract.View::class.java)

    private lateinit var presenter : DetailsPresenter

    companion object {
        val PLANET = FakeData.getPlanet()
    }

    @Before
    fun before() {
        presenter = DetailsPresenter(PLANET.name, view, repo)
    }

    @Test
    fun start() {
        val planet = PLANET
        val params = FakeData.getParams()
        val probes = FakeData.getProbes()

        `when`(repo.getPlanet(PLANET.name)).thenReturn(planet)
        `when`(repo.getParametersByPlanet(PLANET.name)).thenReturn(params)
        `when`(repo.getProbesByPlanet(PLANET.name)).thenReturn(probes)

        presenter.start()

        Mockito.verify(view).showPlanetInfo(planet, params, probes)
    }

    @Test
    fun planetSelected() {
        val planet = PLANET
        val params = FakeData.getParams()
        val probes = FakeData.getProbes()

        `when`(repo.getParametersByPlanet(PLANET.name)).thenReturn(params)
        `when`(repo.getProbesByPlanet(PLANET.name)).thenReturn(probes)

        presenter.planetSelected(planet)
        presenter.planetSelected(planet)

        Mockito.verify(view, times(1)).showPlanetInfo(planet, params, probes)
    }

    @Test
    fun startWrongName() {
        `when`(repo.getPlanet(PLANET.name)).thenReturn(null)

        presenter.start()

        verify(view).close()
    }


    @Test
    fun parameterClicked() {
        val param = FakeData.getParam()
        presenter.parameterClicked(param)
        verify(view).showParameterDetailDialog(param)
    }

    @Test
    fun probeClicked() {
        val probe = FakeData.getProbe()
        presenter.probeClicked(probe)
        verify(view).showProbeDetailDialog(probe)
    }

    @Test
    fun planetWikiClicked() {
        start()
        presenter.planetWikiClicked()
        verify(view).showWebPage(PLANET.wiki)
    }

    @Test
    fun probeWikiClicked() {
        val probe = FakeData.getProbe()
        presenter.probeWikiClicked(probe)
        verify(view).showWebPage(probe.wiki)
    }

    @Test
    fun dialogPlanetClicked() {
        presenter.dialogPlanetClicked(PLANET)
        verify(view).showPlanetDetails(PLANET)
    }

}