package com.maff.planetshandbook.data

/**
 * Created by maff on 11/2/2017.
 */

data class PlanetCategory(
        var name: String,
        val parameters: List<Pair<Parameter, Double>>
)

