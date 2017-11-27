package com.maff.planetshandbook.ui.details

import com.maff.planetshandbook.data.Parameter
import com.maff.planetshandbook.data.Planet
import com.maff.planetshandbook.ui.BasePresenter
import com.maff.planetshandbook.ui.PresenterView

/**
 * Created by maff on 11/26/2017.
 */

interface ParameterDialogContract
{
    interface View : PresenterView<com.maff.planetshandbook.ui.details.ProbeDialogContract.Presenter>
    {
        fun setPresenter(presenter: Presenter)
        fun showPlanet(planet: Planet)
        fun showParameterInfo(parameter: Parameter, valsByPlanet: Collection<Pair<Planet, Double>>)
        fun close()
    }

    interface Presenter : BasePresenter
    {
        fun planetSelected(planet: Planet)
        fun okClicked()
    }
}