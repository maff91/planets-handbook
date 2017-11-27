package com.maff.planetshandbook.ui.details

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.maff.planetshandbook.FlagDrawables
import com.maff.planetshandbook.R
import com.maff.planetshandbook.data.Parameter
import com.maff.planetshandbook.data.PlanetCategory
import com.maff.planetshandbook.data.Probe
import com.maff.planetshandbook.ui.widgets.MultiIconView

/**
 * Created by maff on 11/24/2017.
 */

class DetailsAdapter(
        context: Context,
        categories : Collection<PlanetCategory>,
        probes: Collection<Probe>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private companion object {
        val VIEW_TYPE_HEADER = 0
        val VIEW_TYPE_PARAM = 1
        val VIEW_TYPE_PROBE = 2
    }

    private data class ViewTypeEntry(var type: Int, var localPos: Int)
    private val viewTypeMapping: MutableList<ViewTypeEntry>

    private val headers: MutableList<String>
    private val parameters: MutableList<Pair<Parameter, Double>>
    private val probes : MutableList<Probe> = ArrayList(probes)

    private val headerOddBg: Int
    private val headerEvenBg: Int

    var listener: Listener? = null

    init {
        headerOddBg = context.resources.getColor(R.color.header_odd_bg)
        headerEvenBg = context.resources.getColor(R.color.header_even_bg)

        // Gen headers
        headers = ArrayList()
        categories.mapTo(headers) { it.name }
        if(probes.isNotEmpty()) {
            headers.add(context.getString(R.string.probesHeaderText))
        }

        // Map view types
        viewTypeMapping = ArrayList()

        var titleId = 0
        var paramId = 0

        for(cat in categories)
        {
            viewTypeMapping.add(ViewTypeEntry(VIEW_TYPE_HEADER, titleId++))

            for (par in cat.parameters) {
                viewTypeMapping.add(ViewTypeEntry(VIEW_TYPE_PARAM, paramId++))
            }
        }

        if(probes.isNotEmpty()) {
            viewTypeMapping.add(ViewTypeEntry(VIEW_TYPE_HEADER, titleId++))
            for(i in probes.indices) {
                viewTypeMapping.add(ViewTypeEntry(VIEW_TYPE_PROBE, i))
            }
        }

        // Concat parameters
        parameters = ArrayList()
        for(cat in categories) {
            parameters.addAll(cat.parameters)
        }
    }

    override fun getItemCount(): Int {
        return viewTypeMapping.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        when(viewType) {
            VIEW_TYPE_HEADER -> {
                val v = inflater.inflate(R.layout.details_item_header, parent, false)
                return HeaderViewHolder(v)
            }
            VIEW_TYPE_PARAM -> {
                val v = inflater.inflate(R.layout.details_item_param, parent, false)
                return ParamViewHolder(v)
            }
            VIEW_TYPE_PROBE -> {
                val v =inflater.inflate(R.layout.details_item_probe, parent, false)
                return ProbeViewHolder(v)
            }
            else ->  throw IllegalStateException("Should never get here")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val localPos = viewTypeMapping[position].localPos

        when(holder) {
            is HeaderViewHolder -> {
                holder.title.text = headers[localPos]
                val isOddPos = (localPos + 1) % 2 > 0
                holder.background.setBackgroundColor(if(isOddPos) headerOddBg else headerEvenBg)
            }
            is ParamViewHolder -> {
                val param = parameters[localPos]
                holder.title.text = param.first.name
                holder.value.text = String.format(param.first.format, param.second)

                holder.itemView.setOnClickListener {
                    listener?.paramSelected(param.first)
                }
            }
            is ProbeViewHolder -> {
                val probe = probes[localPos]
                holder.name.text = probe.name

                if(probe.countries.isNotEmpty() ) {
                    val reIds = probe.countries.mapNotNull { FlagDrawables.getFlag(it) }
                    holder.countryImage.setImageResIds(reIds)
                }

                holder.wikiIcon.setOnClickListener {
                    listener?.wikiRequiredFor(probe)
                }

                holder.itemView.setOnClickListener {
                    listener?.probeSelected(probe)
                }
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return viewTypeMapping.get(position).type
    }

    // ViewHolders

    private class HeaderViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        val title: TextView = v.findViewById(R.id.title)
        val background: View = v
    }

    private class ParamViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        val title: TextView = v.findViewById(R.id.title)
        val value: TextView = v.findViewById(R.id.value)
    }

    private class ProbeViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        val name: TextView = v.findViewById(R.id.name)
        val countryImage: MultiIconView = v.findViewById(R.id.flags)
        val wikiIcon: ImageView = v.findViewById(R.id.wikiBtn)
    }

    // Listener

    interface Listener {
        fun paramSelected(param: Parameter)
        fun probeSelected(probe: Probe)
        fun wikiRequiredFor(probe: Probe)
    }
}