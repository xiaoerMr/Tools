package com.sai.utils.permission

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class Buidler(private val permissionUtils: PermissionUtils) {

    private lateinit var activity: FragmentActivity
    private lateinit var fragment: Fragment
    private lateinit var permissions: List<String>

    fun activity(activity: FragmentActivity): Buidler {
        this.activity = activity
        return this
    }

    fun getActivity() = activity

    fun fragment(fragment: Fragment): Buidler {
        this.fragment = fragment
        return this
    }

    fun getFragment() = fragment

    fun permissions(permissions: List<String>): PermissionUtils {
        this.permissions = permissions
        return permissionUtils
    }

    fun permissions(vararg permissions: String): PermissionUtils {
        this.permissions = permissions.asList()
        return permissionUtils
    }

    fun getPermissions() = permissions

//  fun budler():PermissionUtils{
//      return permissionUtils
//  }
}