package com.sai.utils.other

import android.content.res.Resources

object ConvertUtils {

    val density = Resources.getSystem().displayMetrics.density;

    fun dp2px(dpValue: Float): Int {
        return (dpValue * density + 0.5f).toInt()
    }

    fun dp2px(dpValue: Int): Int {
        return (dpValue * density + 0.5f).toInt()
    }
}