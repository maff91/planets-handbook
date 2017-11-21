package com.maff.planetshandbook.ui.list

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import com.maff.planetshandbook.R
import com.maff.planetshandbook.ui.HideTitleOffsetListener
import com.maff.planetshandbook.ui.details.DetailsActivity

import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setSupportActionBar(toolbar)
        appBarLayout.addOnOffsetChangedListener(HideTitleOffsetListener(collapsingToolbarLayout))

        val adapter = MainListAdapter()
        adapter.setOnItemClickListener { startActivity(Intent(this@ListActivity, DetailsActivity::class.java)) }
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_list, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
