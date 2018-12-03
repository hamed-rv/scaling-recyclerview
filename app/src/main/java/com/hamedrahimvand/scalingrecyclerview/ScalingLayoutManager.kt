package com.hamedrahimvand.scalingrecyclerview

import android.content.Context
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * Created By Hamed Rahimvand on 3 December 2018
 */

class ScalingLayoutManager(context: Context, orientation: Int, reverseLayout: Boolean) :
    LinearLayoutManager(context, orientation, reverseLayout) {
    constructor (context: Context, orientation: Int) : this(context, orientation, false)
    constructor(context: Context) : this(context, RecyclerView.VERTICAL, false)

    private val mShrinkAmount = 0.3f
    private val mShrinkDistance = 0.9f

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {

        return scale(orientation, dy, recycler, state)
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        return scale(orientation, dx, recycler, state)
    }

    fun scale(
        orientation: Int, dxORdy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        when (orientation) {
            HORIZONTAL -> {
                val scrolled = super.scrollHorizontallyBy(dxORdy, recycler, state)

                val midpoint = width / 2f
                val d0 = 0f
                val d1 = mShrinkDistance * midpoint
                val s0 = 1f
                val s1 = 1f - mShrinkAmount
                for (i in 0 until childCount) {
                    val child = getChildAt(i)
                    val childMidpoint = (getDecoratedRight(child!!) + getDecoratedLeft(child)) / 2f
                    val d = Math.min(d1, Math.abs(midpoint - childMidpoint))
                    val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
                    child.scaleX = scale
                    child.scaleY = scale

                    val frlSecondLayout = child.findViewById<FrameLayout>(R.id.frl_secondLayout)
                    val frlThirdLayout = child.findViewById<FrameLayout>(R.id.frl_thirdLayout)

                    frlSecondLayout.scaleX = scale + 0.2f
                    frlSecondLayout.scaleY = scale + 0.2f
                    frlThirdLayout.scaleX = scale + 0.45f
                    frlThirdLayout.scaleY = scale + 0.45f
                }
                return scrolled
            }
            VERTICAL -> {
                val scrolled = super.scrollVerticallyBy(dxORdy, recycler, state)
                val midpoint = height / 2f
                val d0 = 0f
                val d1 = mShrinkDistance * midpoint
                val s0 = 1f
                val s1 = 1f - mShrinkAmount
                for (i in 0 until childCount) {
                    val child = getChildAt(i)
                    val childMidpoint = (getDecoratedBottom(child!!) + getDecoratedTop(child)) / 2f
                    val d = Math.min(d1, Math.abs(midpoint - childMidpoint))
                    val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
                    child.scaleX = scale
                    child.scaleY = scale

                    val frlSecondLayout = child.findViewById<FrameLayout>(R.id.frl_secondLayout)
                    val frlThirdLayout = child.findViewById<FrameLayout>(R.id.frl_thirdLayout)

                    frlSecondLayout.scaleX = scale + 0.2f
                    frlSecondLayout.scaleY = scale + 0.2f
                    frlThirdLayout.scaleX = scale + 0.45f
                    frlThirdLayout.scaleY = scale + 0.45f

                }
                return scrolled
            }
            else -> return 0
        }
    }
}