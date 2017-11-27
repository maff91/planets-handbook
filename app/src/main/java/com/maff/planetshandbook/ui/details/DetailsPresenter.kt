package com.maff.planetshandbook.ui.details

import android.net.Uri
import android.webkit.URLUtil
import com.maff.planetshandbook.data.Parameter
import com.maff.planetshandbook.data.Planet
import com.maff.planetshandbook.data.Probe
import com.maff.planetshandbook.data.Repository

/**
 * Created by maff on 11/4/2017.
 */

class DetailsPresenter(
        private val planetName: String,
        private val view:  DetailsContract.View,
        private val repository: Repository
) : DetailsContract.Presenter
{
    lateinit var planet: Planet

    override fun start()
    {
        val planet = repository.getPlanet(planetName)

        if(planet != null) {
            this.planet = planet

            view.showPlanetInfo(
                    planet,
                    repository.getParametersByPlanet(planetName),
                    repository.getProbesByPlanet(planetName))
        }
        else
        {
            view.close()
        }
    }

    override fun planetSelected(planet: Planet) {
        //Ignore in case the same planet has been chosen
        if(planet.name == planetName) {
            return
        }

        view.showPlanetInfo(
                planet,
                repository.getParametersByPlanet(planetName),
                repository.getProbesByPlanet(planetName))
    }

    override fun parameterClicked(parameter: Parameter)
    {
        view.showParameterDetailDialog(parameter)
    }

    override fun probeClicked(probe: Probe)
    {
        view.showProbeDetailDialog(probe)
    }

    override fun planetWikiClicked()
    {
        if (!planet.wiki.isEmpty())
        {
            view.showWebPage(planet.wiki)
        }
    }

    override fun probeWikiClicked(probe: Probe)
    {
        if (!probe.wiki.isEmpty())
        {
            view.showWebPage(probe.wiki)
        }
    }

    override fun dialogPlanetClicked(planet: Planet)
    {
        view.showPlanetDetails(planet)
    }

}