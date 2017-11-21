package com.maff.planetshandbook.ui.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by michael on 19/11/2017.
 */

class MainListAdapter : RecyclerView.Adapter<MainListAdapter.ViewHolder>()
{
    private val items = listOf("1", "2", "1", "1", "1", "1", "1", "100500", "1", "1", "1", "1", "1", "1")
    private var listener: ((Int) -> Unit)? = null

    override fun onBindViewHolder(holder: MainListAdapter.ViewHolder?, position: Int) {
        holder?.name?.text = items[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        val viewHolder = ViewHolder(view)

        view.setOnClickListener { listener?.invoke(viewHolder.adapterPosition) }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val name : TextView = itemView.findViewById(android.R.id.text1)
    }

    fun setOnItemClickListener(listener: ((pos: Int) -> Unit)?) {
        this.listener = listener
    }
}