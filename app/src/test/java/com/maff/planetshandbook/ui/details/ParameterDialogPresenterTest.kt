package com.maff.planetshandbook.ui.details

import com.maff.planetshandbook.data.FakeData
import com.maff.planetshandbook.data.Repository
import com.maff.planetshandbook.ui.details.ParameterDialogContract.Presenter
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

/**
 * Created by maff on 11/27/2017.
 */

class ParameterDialogPresenterTest {
    private val repo: Repository = Mockito.mock(Repository::class.java)
    private val view: ParameterDialogContract.View = Mockito.mock(ParameterDialogContract.View::class.java)

    private lateinit var presenter : Presenter

    companion object {
        val PARAMETER = FakeData.getParam()
    }

    @Before
    fun before() {
        presenter = ParameterDialogPresenter(PARAMETER, view, repo)
    }

    @Test
    fun start() {
        presenter.start()
        Mockito.verify(view).showParameterInfo(PARAMETER, ArgumentMatchers.anyCollection())
    }

    @Test
    fun okClicked() {
        presenter.okClicked()
        Mockito.verify(view).close()
    }

    @Test
    fun planetSelected() {
        val planet = FakeData.getPlanet()
        presenter.planetSelected(planet)
        Mockito.verify(view).showPlanet(planet)
    }

}
