package com.sai.tools

import android.content.Context
import android.widget.Toast

class ToastUtils {

    fun show(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}