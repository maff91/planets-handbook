package com.maff.planetshandbook.ui.details

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.maff.planetshandbook.PlanetDrawables
import com.maff.planetshandbook.R
import com.maff.planetshandbook.data.Planet
import com.maff.planetshandbook.data.Probe

/**
 * Created by maff on 11/25/2017.
 */

class ProbeDialogFragment : DialogFragment(), ProbeDialogContract.View {
    private lateinit var presenter: ProbeDialogContract.Presenter

    private lateinit var recycler: RecyclerView
    private lateinit var title: TextView

    interface Listener {
        fun showPlanet(planet: Planet)
    }

    var listener: Listener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.list_dialog, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        title = view.findViewById(R.id.title)
        recycler = view.findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        view.findViewById<Button>(R.id.button).setOnClickListener { presenter.okClicked() }

        presenter.start()
    }

    override fun setPresenter(presenter: ProbeDialogContract.Presenter) {
        this.presenter = presenter
    }

    override fun showProbeInfo(probe: Probe, visitedPlanets: Collection<Planet>) {
        title.text = probe.name
        recycler.adapter = RecyclerAdapter(visitedPlanets)
    }

    override fun showPlanet(planet: Planet) {
        listener?.showPlanet(planet)
        dismiss()
    }

    override fun close() {
        dismiss()
    }

    private inner class RecyclerAdapter(planets: Collection<Planet>) : RecyclerView.Adapter<ViewHolder>() {
        private val planets = ArrayList(planets)

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val planet = planets[position]

            holder.name.text = planet.name
            PlanetDrawables.getSmallIcon(planet)?.let {
                holder.image.setImageResource(it)
            }
        }

        override fun getItemCount(): Int {
            return planets.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.probe_dialog_item, parent, false)
            val viewHolder = ViewHolder(v)

            v.setOnClickListener {
                val pos = viewHolder.adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    presenter.planetSelected(planets[pos])
                }
            }

            return viewHolder
        }
    }

    private class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val image: ImageView = v.findViewById(R.id.planetImage)
        val name: TextView = v.findViewById(R.id.name)
    }
}