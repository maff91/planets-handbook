package com.maff.planetshandbook.ui.details

import com.maff.planetshandbook.data.Planet
import com.maff.planetshandbook.data.Probe
import com.maff.planetshandbook.data.Repository

/**
 * Created by maff on 11/26/2017.
 */
class ProbeDialogPresenter(private val probe: Probe,
                           private val view: ProbeDialogContract.View,
                           private val repository: Repository)
    : ProbeDialogContract.Presenter
{
    init {
        view.setPresenter(this)
    }

    override fun start() {
        val visitedPlanets = repository.getAllPlanets().filter { probe.planets.contains(it.name) }
        view.showProbeInfo(probe, visitedPlanets)
    }

    override fun planetSelected(planet: Planet) {
        view.showPlanet(planet)
    }

    override fun okClicked() {
        view.close()
    }

}