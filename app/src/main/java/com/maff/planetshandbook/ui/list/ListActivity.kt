package com.maff.planetshandbook.ui.list

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.maff.planetshandbook.PlanetsHandbookApp
import com.maff.planetshandbook.R
import com.maff.planetshandbook.data.Planet
import com.maff.planetshandbook.ui.AboutActivity
import com.maff.planetshandbook.ui.HideTitleOffsetListener
import com.maff.planetshandbook.ui.details.DetailsActivity

import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(), ListContract.View {
    private lateinit var presenter: ListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setSupportActionBar(toolbar)
        appBarLayout.addOnOffsetChangedListener(HideTitleOffsetListener(collapsingToolbarLayout))

        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        presenter = ListPresenter(this, PlanetsHandbookApp.repository)
        presenter.start()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.aboutBtn) {
            presenter.aboutBtnClicked()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showPlanets(planets: Collection<Planet>) {
        val adapter = PlanetListAdapter(planets)

        adapter.setItemClickListener { p -> startDetailsActivity(p) }

        recycler.adapter = adapter
    }


    override fun showPlanetDetails(planet: Planet) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAboutPage() {
        startActivity(Intent(this, AboutActivity::class.java))
    }

    private fun startDetailsActivity(planet: Planet) {
        val intent = Intent(this@ListActivity, DetailsActivity::class.java)
        intent.extras.putString(DetailsActivity.PLANET_NAME_EXTRA, planet.shortName)
        startActivity(intent)
    }
}
