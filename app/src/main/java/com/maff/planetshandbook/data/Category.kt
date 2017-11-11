package com.maff.planetshandbook.data

/**
 * Created by maff on 10/31/2017.
 */

data class Category(
        var name: String? = null,
        val parameters: Map<String, Parameter>
)