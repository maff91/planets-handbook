package com.maff.planetshandbook.ui.details

import android.net.sip.SipSession
import com.maff.planetshandbook.data.FakeData
import com.maff.planetshandbook.data.Planet
import com.maff.planetshandbook.data.Repository
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*


/**
 * Created by maff on 11/26/2017.
 */

class ProbeDialogPresenterTest {
    private val repo: Repository = Mockito.mock(Repository::class.java)
    private val view: ProbeDialogContract.View = Mockito.mock(ProbeDialogContract.View::class.java)

    private lateinit var presenter : ProbeDialogPresenter

    companion object {
        val PROBE = FakeData.getProbe()
    }

    @Before
    fun before() {
        presenter = ProbeDialogPresenter(PROBE, view, repo)
    }

    @Test
    fun start() {
        presenter.start()
        verify(view).showProbeInfo(PROBE, ArgumentMatchers.anyCollection())
    }

    @Test
    fun okClicked() {
        presenter.okClicked()
        verify(view).close()
    }

    @Test
    fun planetSelected() {
        val planet = FakeData.getPlanet()
        presenter.planetSelected(planet)
        verify(view).showPlanet(planet)
    }

}
