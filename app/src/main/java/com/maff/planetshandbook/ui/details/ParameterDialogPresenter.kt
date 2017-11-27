package com.maff.planetshandbook.ui.details

import com.maff.planetshandbook.data.Parameter
import com.maff.planetshandbook.data.Planet
import com.maff.planetshandbook.data.Repository

/**
 * Created by maff on 11/27/2017.
 */

class ParameterDialogPresenter(private val parameter: Parameter,
                               private val view: ParameterDialogContract.View,
                               private val repository: Repository)
    : ParameterDialogContract.Presenter
{
    init {
        view.setPresenter(this)
    }

    override fun start() {
        val valsByPlanet = parameter.values.entries.mapNotNull {
            repository.getPlanet(it.key)?.let {
                planet -> return@mapNotNull Pair(planet, it.value)
            }
        }.sortedBy { it.second }

        view.showParameterInfo(parameter, valsByPlanet)
    }

    override fun planetSelected(planet: Planet) {
        view.showPlanet(planet)
    }

    override fun okClicked() {
        view.close()
    }
}