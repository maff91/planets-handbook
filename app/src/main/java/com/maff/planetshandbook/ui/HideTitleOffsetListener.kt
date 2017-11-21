package com.maff.planetshandbook.ui

import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.util.Log

/**
 * Created by michael on 20/11/2017.
 */

class HideTitleOffsetListener(private val toolbar: CollapsingToolbarLayout)
    : AppBarLayout.OnOffsetChangedListener
{
    private var isShow = true
    private var scrollRange = -1
    private var originalTitle = toolbar.title

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (scrollRange == -1) {
            scrollRange = appBarLayout.totalScrollRange
        }

        val fraction = -verticalOffset.toFloat() / scrollRange.toFloat()

        if (fraction > 0.65f) {
            Log.d("TEST", fraction.toString())
            toolbar.title = originalTitle
            isShow = true
        } else if(isShow) {
            toolbar.title = " "
            isShow = false
        }
    }
}