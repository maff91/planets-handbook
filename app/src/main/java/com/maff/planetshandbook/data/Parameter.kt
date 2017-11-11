package com.maff.planetshandbook.data

/**
 * Created by maff on 10/31/2017.
 */

data class Parameter(
        var name: String? = null,
        val format: String,
        val values: Map<String, Double>
)