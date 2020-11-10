package com.sai.utils.permission

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.permissionx.guolindev.PermissionX
import java.lang.RuntimeException

class PermissionUtils {

    private lateinit var buidler: Buidler

    /**
     * 构建请求权限结构，首先调用该方法
     * 最后调用 callback（） 方法
     */
    fun create(): Buidler {
        buidler = Buidler(this)
        return buidler
    }

    /**
     * 请求权限的回调
     */
    fun callback(callback: PermissionCallback) {

        if (buidler.getActivity() == null || buidler.getFragment() == null){
            throw RuntimeException(" -- PermissionUtils -- 请设置 Activity 或 Fragment")
        }

        if (buidler.getPermissions() == null || buidler.getPermissions().isEmpty()){
            throw RuntimeException(" -- PermissionUtils -- 请设置请求的权限")
        }


        if (buidler.getActivity() != null) {
            show(
                activity = buidler.getActivity(),
                permissions = buidler.getPermissions(),
                callback = callback
            )
        } else {
            show(
                fragment = buidler.getFragment(),
                permissions = buidler.getPermissions(),
                callback = callback
            )
        }
    }

    /**
     *  检查单个权限是否允许
     */
    fun checkPermissions(context: Context, permission: String): Boolean {
        return PermissionX.isGranted(context, permission)
    }

    private fun show(fragment: Fragment, permissions: List<String>, callback: PermissionCallback) {
        PermissionX
            .init(fragment)
            .permissions(permissions)
            .request { allGranted, _, deniedList ->

                if (allGranted)  callback.onAllGranted()
                else callback.onDeniedList(deniedList)
            }
    }

    private fun show(
        activity: FragmentActivity,
        permissions: List<String>,
        callback: PermissionCallback
    ) {
        PermissionX
            .init(activity)
            .permissions(permissions)
            .request { allGranted, _, deniedList ->

                if (allGranted)  callback.onAllGranted()
                else callback.onDeniedList(deniedList)
            }
    }
}