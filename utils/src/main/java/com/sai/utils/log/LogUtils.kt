package com.sai.utils.log

import android.util.Log
import com.sai.utils.LogTag


object LogUtils {

    fun e(msg: String) {
        Log.e(LogTag, msg)
    }
}