package com.maff.planetshandbook.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout

/**
 * Created by maff on 11/27/2017.
 */

class MultiIconView : LinearLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var sampleParams: ViewGroup.LayoutParams? = null

    fun setImageResIds(resourceIds: Collection<Int>) {
        if(sampleParams == null)
        {
            if(childCount != 0) {
                sampleParams = getChildAt(0).layoutParams
            }
            else {
                throw IllegalStateException("Place at least one image to be duplicated")
            }
        }

        if(resourceIds.isEmpty()) {
            removeAllViews()
            return
        }

        removeNonImageViews()

        // Remove redundant views
        if(childCount > resourceIds.size) {
            removeViews(0, childCount - resourceIds.size)
        }

        // Fill images with resIds
        for((index, resId) in resourceIds.withIndex())
        {
            if(index > (childCount - 1))
            {
                val view = ImageView(context)
                view.layoutParams = sampleParams
                view.setImageResource(resId)
                addView(view)
            }
            else
            {
                val view = getChildAt(index) as ImageView
                view.layoutParams = sampleParams
                view.setImageResource(resId)
            }
        }
    }

    private fun removeNonImageViews() {
        var i = 0

        while (i < childCount)
        {
            if(getChildAt(i) !is ImageView) {
                removeViewAt(i)
                continue
            }
            i++
        }
    }
}