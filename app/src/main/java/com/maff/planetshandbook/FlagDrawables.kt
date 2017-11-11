package com.maff.planetshandbook

import android.graphics.drawable.Drawable

/**
 * Created by maff on 11/5/2017.
 */

object FlagDrawables
{
    fun getFlag(name: String) : Int?
    {
        return flags[name]
    }

    private val flags = hashMapOf(
            Pair("US", R.drawable.flag_usa),
            Pair("EU", R.drawable.flag_eu),
            Pair("CH", R.drawable.flag_china),
            Pair("IT", R.drawable.flag_italy),
            Pair("DE", R.drawable.flag_germany),
            Pair("USSR", R.drawable.flag_ussr),
            Pair("JP", R.drawable.flag_japan)
    )
}