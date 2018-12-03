package com.hamedrahimvand.scalingrecyclerview.utils

import android.content.res.Resources
/**
 * Created By Hamed Rahimvand on 3 December 2018
 */

class ConverterUtils {

    companion object {
        fun dpToPx(dp: Float): Float {
            return (dp * Resources.getSystem().displayMetrics.density)
        }

        fun pxToDp(px: Float): Float {
            return (px / Resources.getSystem().displayMetrics.density)
        }

    }
}