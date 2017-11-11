package com.maff.planetshandbook.data

/**
 * Created by maff on 10/31/2017.
 */

data class Probe(
        var name: String? = null,
        val wiki: String,
        val countries: List<String>,
        val planets: List<String>
)
