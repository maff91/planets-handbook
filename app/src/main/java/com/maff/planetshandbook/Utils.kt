package com.maff.planetshandbook

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * Created by maff on 11/8/2017.
 */

object Utils
{
    fun openWebPage(context: Context, page: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(page)
        context.startActivity(intent)
    }
}