package com.maff.planetshandbook.ui.list

import android.net.Uri
import com.maff.planetshandbook.data.Planet
import com.maff.planetshandbook.ui.BasePresenter
import com.maff.planetshandbook.ui.PresenterView

/**
 * Created by maff on 11/1/2017.
 */

interface ListContract
{
    interface View : PresenterView<ListContract.Presenter>
    {
        fun showPlanets(planets: Collection<Planet>)
        fun showPlanetDetails(planet: Planet)
        fun showAboutPage()
    }

    interface Presenter : BasePresenter
    {
        fun planetClicked(planet: Planet)
        fun aboutBtnClicked()
    }
}