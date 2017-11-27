package com.maff.planetshandbook.ui.details

import com.maff.planetshandbook.data.Planet
import com.maff.planetshandbook.data.Probe
import com.maff.planetshandbook.ui.BasePresenter
import com.maff.planetshandbook.ui.PresenterView

/**
 * Created by maff on 11/26/2017.
 */

interface ProbeDialogContract {
    interface View : PresenterView<ProbeDialogContract.Presenter>
    {
        fun setPresenter(presenter: Presenter)
        fun showPlanet(planet: Planet)
        fun showProbeInfo(probe: Probe, visitedPlanets: Collection<Planet>)
        fun close()
    }
    interface Presenter : BasePresenter
    {
        fun planetSelected(planet: Planet)
        fun okClicked()
    }
}

