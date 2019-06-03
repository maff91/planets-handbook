package com.maff.planetshandbook.ui.list

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.maff.planetshandbook.PlanetDrawables
import com.maff.planetshandbook.R
import com.maff.planetshandbook.data.Planet

/**
 * Created by michael on 21/11/2017.
 */

class PlanetListAdapter(planets: Collection<Planet>) : RecyclerView.Adapter<PlanetListAdapter.ViewHolder>()
{
    private var itemClickListener: ((Planet) -> Unit)? = null
    private val planets = ArrayList(planets)

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val planet = planets[position]

        PlanetDrawables.getListItem(planet)?.let {
            holder.image.setImageResource(it)
        }

        holder.name.text = planet.name
    }

    override fun getItemCount(): Int
    {
        return planets.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.planet_list_item, null)
        val holder = ViewHolder(view)

        view.setOnClickListener {
            val pos = holder.adapterPosition
            if(itemClickListener != null && pos != RecyclerView.NO_POSITION) {
                itemClickListener!!.invoke(planets[pos])
            }
        }

        return holder
    }

    fun setItemClickListener(listener: ((Planet) -> Unit)?)
    {
        itemClickListener = listener
    }

    class ViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView)
    {
        val image = rootView.findViewById<ImageView>(R.id.image)
        val name = rootView.findViewById<TextView>(R.id.name)
    }
}