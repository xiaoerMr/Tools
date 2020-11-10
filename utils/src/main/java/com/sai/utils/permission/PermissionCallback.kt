package com.sai.utils.permission

interface PermissionCallback {
    fun onAllGranted()
    fun onDeniedList(denied: List<String>)
}