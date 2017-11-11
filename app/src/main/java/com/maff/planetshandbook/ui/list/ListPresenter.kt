package com.maff.planetshandbook.ui.list

import com.maff.planetshandbook.data.Repository
import com.maff.planetshandbook.data.Planet

/**
 * Created by maff on 11/3/2017.
 */

class ListPresenter(view: ListContract.View, repo: Repository) : ListContract.Presenter
{
    private val repository = repo
    private val view = view

    init
    {
        view.setPresenter(this)
    }

    override fun start()
    {
        view.showPlanets(repository.getAllPlanets())
    }

    override fun planetClicked(planet: Planet)
    {
        view.showPlanetDetails(planet)
    }

    override fun aboutBtnClicked()
    {
        view.showAboutPage()
    }
}