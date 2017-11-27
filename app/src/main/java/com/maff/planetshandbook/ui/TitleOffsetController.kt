package com.maff.planetshandbook.ui

import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout

/**
 * Created by michael on 20/11/2017.
 */

class TitleOffsetController(private val toolbar: CollapsingToolbarLayout)
    : AppBarLayout.OnOffsetChangedListener {
    private var isShow = true
    private var scrollRange = -1
    private var realTitle: CharSequence

    init {
        realTitle = toolbar.title ?: " "
    }

    var title: CharSequence
        get() = realTitle
        set(value) {
            realTitle = value
            if(isShow) {
                toolbar.title = realTitle
            }
        }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (scrollRange == -1) {
            scrollRange = appBarLayout.totalScrollRange
        }

        val fraction = -verticalOffset.toFloat() / scrollRange.toFloat()

        if (fraction > 0.65f) {
            toolbar.title = realTitle
            isShow = true
        } else if(isShow) {
            toolbar.title = " "
            isShow = false
        }
    }
}