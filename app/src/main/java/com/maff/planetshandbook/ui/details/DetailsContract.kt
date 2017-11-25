package com.maff.planetshandbook.ui.details

import com.maff.planetshandbook.data.*
import com.maff.planetshandbook.ui.BasePresenter
import com.maff.planetshandbook.ui.PresenterView

/**
 * Created by maff on 11/1/2017.
 */

interface DetailsContract
{
    interface View : PresenterView<DetailsContract.Presenter>
    {
        fun showPlanetInfo(
                planet: Planet,
                categories: Collection<PlanetCategory>,
                probes: Collection<Probe>)
        fun showParameterDetailDialog(parameter: Parameter)
        fun showProbeDetailDialog(probe: Probe)
        fun showWebPage(url: String)
        fun showPlanetDetails(planet: Planet) {}
        fun close()
    }

    interface Presenter : BasePresenter
    {
        fun parameterClicked(parameter: Parameter)
        fun probeClicked(probe: Probe)
        fun planetWikiClicked()
        fun probeWikiClicked(probe: Probe)
        fun dialogPlanetClicked(planet: Planet)
    }
}