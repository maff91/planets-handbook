package com.maff.planetshandbook.ui.details

import android.app.DialogFragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.maff.planetshandbook.PlanetDrawables
import com.maff.planetshandbook.R
import com.maff.planetshandbook.data.Parameter
import com.maff.planetshandbook.data.Planet

/**
 * Created by maff on 11/25/2017.
 */

class ParameterDialogFragment() : DialogFragment(), ParameterDialogContract.View {
    private lateinit var presenter: ParameterDialogContract.Presenter

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

        view.findViewById<Button>(R.id.button).setOnClickListener{ presenter.okClicked() }

        presenter.start()
    }

    override fun setPresenter(presenter: ParameterDialogContract.Presenter) {
        this.presenter = presenter
    }

    override fun showPlanet(planet: Planet) {
        listener?.showPlanet(planet)
        dismiss()
    }

    override fun showParameterInfo(parameter: Parameter, valsByPlanet: Collection<Pair<Planet, Double>>) {
        title.text = parameter.name
        recycler.adapter = RecyclerAdapter(parameter, valsByPlanet)
    }

    override fun close() {
        dismiss()
    }

    private inner class RecyclerAdapter(private val parameter: Parameter, valsByPlanet: Collection<Pair<Planet, Double>>)
        : RecyclerView.Adapter<ViewHolder>()
    {
        private val valsByPlanet = ArrayList(valsByPlanet)

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val valueWithPlanet = valsByPlanet[position]
            val planet = valueWithPlanet.first
            val value = valueWithPlanet.second

            holder.planetName.text = planet.shortName
            PlanetDrawables.getSmallIcon(planet)?.let {
                holder.image.setImageResource(it)
            }

            holder.value.text = String.format(parameter.format, value)
        }

        override fun getItemCount(): Int {
            return valsByPlanet.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.param_dialog_item, parent, false)
            val viewHolder = ViewHolder(v)

            v.setOnClickListener {
                val pos = viewHolder.adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    presenter.planetSelected(valsByPlanet[pos].first)
                }
            }

            return viewHolder
        }
    }

    private class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val image: ImageView = v.findViewById(R.id.planetImage)
        val planetName: TextView = v.findViewById(R.id.planetName)
        val value: TextView = v.findViewById(R.id.value)
    }
}