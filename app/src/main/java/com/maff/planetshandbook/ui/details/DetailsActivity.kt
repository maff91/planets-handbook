package com.maff.planetshandbook.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.maff.planetshandbook.PlanetDrawables
import com.maff.planetshandbook.PlanetsHandbookApp
import com.maff.planetshandbook.R
import com.maff.planetshandbook.Utils
import com.maff.planetshandbook.data.Parameter
import com.maff.planetshandbook.data.Planet
import com.maff.planetshandbook.data.PlanetCategory
import com.maff.planetshandbook.data.Probe
import com.maff.planetshandbook.ui.TitleOffsetController
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), DetailsContract.View,
        ProbeDialogFragment.Listener, ParameterDialogFragment.Listener
{
    companion object {
        val PLANET_NAME_EXTRA: String = "planetName"
    }

    private lateinit var presenter: DetailsContract.Presenter
    private lateinit var titleController: TitleOffsetController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        titleController = TitleOffsetController(collapsingToolbarLayout)
        appBarLayout.addOnOffsetChangedListener(titleController)

        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val planetName = if(intent.extras != null) intent.extras.getString(PLANET_NAME_EXTRA, "") else  ""

        presenter = DetailsPresenter(planetName, this, PlanetsHandbookApp.repository)
        presenter.start()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.menu_activity_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> finish()
            R.id.wikiBtn -> presenter.planetWikiClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showPlanetInfo(planet: Planet, categories: Collection<PlanetCategory>, probes: Collection<Probe>) {
        titleController.title = planet.name
        planetName.text = planet.name

        PlanetDrawables.getHeader(planet)?.let {
            planetImage.setImageResource(it)
        }

        val adapter =  DetailsAdapter(this, categories, probes)
        adapter.listener = object : DetailsAdapter.Listener
        {
            override fun paramSelected(param: Parameter) {
                presenter.parameterClicked(param)
            }

            override fun probeSelected(probe: Probe) {
                presenter.probeClicked(probe)
            }

            override fun wikiRequiredFor(probe: Probe) {
                presenter.probeWikiClicked(probe)
            }

        }
        recycler.adapter = adapter
    }

    override fun showParameterDetailDialog(parameter: Parameter) {
        if(isAnyDialogOpened())
            return

        val dialog = ParameterDialogFragment()
        ParameterDialogPresenter(parameter, dialog, PlanetsHandbookApp.repository)
        dialog.listener = this
        dialog.show(fragmentManager, ParameterDialogFragment::class.java.simpleName)
    }

    override fun showProbeDetailDialog(probe: Probe) {
        if(isAnyDialogOpened())
            return

        val dialog = ProbeDialogFragment()
        ProbeDialogPresenter(probe, dialog, PlanetsHandbookApp.repository)
        dialog.listener = this
        dialog.show(fragmentManager, ProbeDialogFragment::class.java.simpleName)
    }

    override fun showWebPage(url: String) {
        Utils.openWebPage(this, url)
    }

    override fun close()
    {
        finish()
    }

    override fun showPlanet(planet: Planet) {
        presenter.planetSelected(planet)
        appBarLayout.setExpanded(true, false)
        recycler.scrollToPosition(0)
    }

    fun isAnyDialogOpened(): Boolean
    {
        if(fragmentManager.findFragmentByTag(ProbeDialogFragment::class.java.simpleName) != null) {
            return true
        }

        if(fragmentManager.findFragmentByTag(ParameterDialogFragment::class.java.simpleName) != null) {
            return true
        }

        return false
    }
}
