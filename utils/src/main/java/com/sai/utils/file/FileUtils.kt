package com.sai.utils.file

import android.content.Context
import android.os.Build
import android.os.Environment
import androidx.core.content.ContextCompat


object FileUtils {

    fun getRootPath(context: Context): String {
        // 10版本 Q 以上
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContextCompat.getExternalFilesDirs(context, null)[0].absolutePath
        } else {
            Environment.getExternalStorageDirectory().absolutePath
        }
    }
}