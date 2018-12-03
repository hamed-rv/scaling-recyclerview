package com.hamedrahimvand.scalingrecyclerview.model

import android.view.View
/**
 * Created By Hamed Rahimvand on 3 December 2018
 */

class ScalingRecyclerModel private constructor(
    val firstTitle: String?,
    val imageUrl: String?,
    val secondTitle: String?,
    val visibility: Int?
) {

    data class Builder(
        var firstTitle: String? = null,
        var imageUrl: String? = null,
        var secondTitle: String? = null,
        var visibility: Int? = View.VISIBLE
    ) {

        fun firstTitle(firstTitle: String?) = apply { this.firstTitle = firstTitle }
        fun imageUrl(imageUrl: String?) = apply { this.imageUrl = imageUrl }
        fun secondTitle(secondTitle: String?) = apply { this.secondTitle = secondTitle }
        fun visibility(visibility: Int) = apply { this.visibility = visibility }
        fun build() = ScalingRecyclerModel(firstTitle, imageUrl, secondTitle, visibility)
    }


}