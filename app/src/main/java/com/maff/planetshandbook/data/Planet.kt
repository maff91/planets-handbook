package com.maff.planetshandbook.data

import com.google.gson.annotations.SerializedName

/**
 * Created by maff on 10/31/2017.
 */

data class Planet(
        var name: String,
        @SerializedName("short_name") val shortName: String,
        val wiki: String
)