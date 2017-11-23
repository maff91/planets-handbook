package com.maff.planetshandbook.ui.details

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.maff.planetshandbook.PlanetsHandbookApp
import com.maff.planetshandbook.R
import com.maff.planetshandbook.Utils
import com.maff.planetshandbook.data.Parameter
import com.maff.planetshandbook.data.Planet
import com.maff.planetshandbook.data.PlanetCategory
import com.maff.planetshandbook.data.Probe
import com.maff.planetshandbook.ui.HideTitleOffsetListener
import com.maff.planetshandbook.ui.list.MainListAdapter
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), DetailsContract.View {
    companion object {
        val PLANET_NAME_EXTRA: String = "planetName"
    }

    private lateinit var presenter: DetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appBarLayout.addOnOffsetChangedListener(HideTitleOffsetListener(collapsingToolbarLayout))

        recycler.adapter = MainListAdapter()
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val planetName = if(intent.extras != null) intent.extras.getString(PLANET_NAME_EXTRA, "") else  ""
        presenter = DetailsPresenter(planetName, this, PlanetsHandbookApp.repository)
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showParameterDetailDialog(parameter: Parameter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProbeDetailDialog(probe: Probe) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showWebPage(url: String) {
        Utils.openWebPage(this, url)
    }
}
