package com.maff.planetshandbook

import com.maff.planetshandbook.data.Planet

/**
 * Created by maff on 11/1/2017.
 */

object PlanetDrawables
{
    fun getHeader(planet: Planet) : Int?
    {
        return headers[planet.name]
    }

    fun getSmallIcon(planet: Planet) : Int?
    {
        return smallIcons[planet.name]
    }

    fun getListItem (planet: Planet) : Int?
    {
        return listItems[planet.name]
    }

    private val headers = hashMapOf(
            Pair("Mercury", R.drawable.mercury_header),
            Pair("Venus", R.drawable.venus_header),
            Pair("Earth", R.drawable.earth_header),
            Pair("Mars", R.drawable.mars_header),
            Pair("Jupiter", R.drawable.jupiter_header),
            Pair("Saturn", R.drawable.saturn_header),
            Pair("Uranus", R.drawable.uranus_header),
            Pair("Neptune", R.drawable.neptune_header)
    )
    private val smallIcons = hashMapOf(
            Pair("Mercury", R.drawable.mercury_small),
            Pair("Venus", R.drawable.venus_small),
            Pair("Earth", R.drawable.earth_small),
            Pair("Mars", R.drawable.mars_small),
            Pair("Jupiter", R.drawable.jupiter_small),
            Pair("Saturn", R.drawable.saturn_small),
            Pair("Uranus", R.drawable.uranus_small),
            Pair("Neptune", R.drawable.neptune_small)
    )

    private val listItems = hashMapOf(
            Pair("Mercury", R.drawable.mercury_medium_cut),
            Pair("Venus", R.drawable.venus_medium_cut),
            Pair("Earth", R.drawable.earth_medium_cut),
            Pair("Mars", R.drawable.mars_medium_cut),
            Pair("Jupiter", R.drawable.jupiter_medium_cut),
            Pair("Saturn", R.drawable.saturn_medium_cut),
            Pair("Uranus", R.drawable.uranus_medium_cut),
            Pair("Neptune", R.drawable.neptune_medium_cut)
    )
}