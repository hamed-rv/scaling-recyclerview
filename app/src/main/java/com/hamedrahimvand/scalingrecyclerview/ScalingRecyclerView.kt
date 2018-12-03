package com.hamedrahimvand.scalingrecyclerview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.hamedrahimvand.scalingrecyclerview.adapter.ScalingRecyclerAdapter
import com.hamedrahimvand.scalingrecyclerview.model.ScalingRecyclerModel

/**
 * Created By Hamed Rahimvand on 3 December 2018
 */

class ScalingRecyclerView(context: Context, attributeSet: AttributeSet?, defStyle: Int) :
    ConstraintLayout(context, attributeSet, defStyle) {
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context) : this(context, null, 0)

    lateinit var scalingRecyclerAdapter: ScalingRecyclerAdapter
    lateinit var scalingRecyclerModelScoreList: ArrayList<ScalingRecyclerModel>
    lateinit var recyclerView: RecyclerView
    /**
     * @see snapHelper
     */
    fun setSnap(isSnap: Boolean){
        snapHelper(isSnap)
    }



    init {
        inflate(context, R.layout.scailing_recyclerview, this)
        initRecyclerView()
    }

    /**
     * Base initialize of recyclerView
     */
    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.rcv_scaling)
        recyclerView.setHasFixedSize(true)

        setLayoutOrientation(RecyclerView.HORIZONTAL)
    }

    /**
     * Add snap feature to recyclerView with SnapHelper
     * @see isSnap by default is "false", developer can enable snap feature by set it true
     */
    private fun snapHelper(isSnap: Boolean) {
        if (isSnap) {
            val topSnapHelper = LinearSnapHelper()
            topSnapHelper.attachToRecyclerView(recyclerView)
        }
    }

    /**
     * set custom ScalingLayoutManager that extended of LinearLayoutManager to recyclerView
     * @see ScalingLayoutManager
     * @param orientation Is ScalingRecyclerViw orientation, default value is RecyclerView#HORIZONTAL
     */
    fun setLayoutOrientation(@RecyclerView.Orientation orientation : Int) {
        recyclerView.layoutManager = ScalingLayoutManager(context, orientation, false)
    }


    /**
     * Generate recyclerView Adapter, Notice that if you didn't set recyclerView adapter
     * you get NullPointerExceptions
     */
    fun setAdapter(scalingRecyclerList: ArrayList<ScalingRecyclerModel>) {
        //just for fix snap for first and last item
        scalingRecyclerList.add(
            0,
            ScalingRecyclerModel.Builder().visibility(View.INVISIBLE).build()
        )
        scalingRecyclerList.add(
            scalingRecyclerList.size,
            ScalingRecyclerModel.Builder().visibility(View.INVISIBLE).build()
        )

        //initialize list and adapter
        scalingRecyclerModelScoreList = scalingRecyclerList
        scalingRecyclerAdapter = ScalingRecyclerAdapter(context, scalingRecyclerList)
        recyclerView.adapter = scalingRecyclerAdapter

    }

    fun addItem(item: ScalingRecyclerModel) {
        if (this::scalingRecyclerAdapter.isInitialized && this::scalingRecyclerModelScoreList.isInitialized) {
            scalingRecyclerModelScoreList.add(scalingRecyclerModelScoreList.size-1,item)
            scalingRecyclerAdapter.notifyItemInserted(scalingRecyclerModelScoreList.size)
        } else {
            throw NullPointerException("ScalingAdapter can not be null, at first call setAdapter")
        }
    }

    fun addItem(items: ArrayList<ScalingRecyclerModel>) {
        if (this::scalingRecyclerAdapter.isInitialized && this::scalingRecyclerModelScoreList.isInitialized) {
            scalingRecyclerModelScoreList.addAll(scalingRecyclerModelScoreList.size-1,items)
            scalingRecyclerAdapter.notifyItemInserted(scalingRecyclerModelScoreList.size)
        } else {
            throw NullPointerException("ScalingAdapter can not be null, at first call setAdapter")
        }
    }

    /**
     * @param placeholderDrawable The placeholderDrawable is used for imageView before load image from URL
     */
    fun setPlaceHolder(placeholderDrawable: Drawable?) {
        scalingRecyclerAdapter.placeHolderDrawable = placeholderDrawable
    }

    /**
     * @param onErrorDrawable The onErrorDrawable  is used for imageView when load image from URL is failed
     */
    fun setOnErrorDrawable(onErrorDrawable: Drawable?) {
        scalingRecyclerAdapter.onErrorDrawable = onErrorDrawable
    }

    /**
     * @param resourceId TextAppearance resource id
     */
    fun setFirstTitleTextAppearance(@StyleRes resourceId: Int) {
        scalingRecyclerAdapter.firstTitleTextAppearance = resourceId
    }

    /**
     * @param resourceId TextAppearance resource id
     */
    fun setSecondTitleTextAppearance(@StyleRes resourceId: Int) {
        scalingRecyclerAdapter.secondTitleTextAppearance = resourceId
    }

    fun setFirstLayoutBackground(@DrawableRes resId:Int){
        scalingRecyclerAdapter.firstLayoutBackgroundDrawable = resId
    }
    fun setSecondLayoutBackground(@DrawableRes resId:Int){
        scalingRecyclerAdapter.secondLayoutBackgroundDrawable = resId
    }
    fun setThirdLayoutBackground(@DrawableRes resId:Int){
        scalingRecyclerAdapter.thirdLayoutBackgroundDrawable = resId
    }

}