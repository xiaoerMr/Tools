package com.sai.utils.system

import android.app.ActivityManager
import android.content.Context

object System {
    /**
     * 获取进程名称
     */
    fun getProcessName(context: Context, pid: Int): String? {
        val systemService = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningAppProcesses = systemService.runningAppProcesses

        runningAppProcesses?.let { it ->
            it.forEach {
                if (it.pid == pid) {
                    return it.processName
                }
            }
        }
        return null
    }

}