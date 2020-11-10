package com.sai.tools

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sai.utils.event.LiveDataBus
import com.sai.utils.event.LiveEventBus
import com.sai.utils.permission.PermissionCallback
import com.sai.utils.permission.PermissionUtils
import com.sai.utils.permission_camera
import com.sai.utils.permission_read_contacts
import com.sai.utils.toast.ToastUtils

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        LiveEventBus.get().with(Event_Type_Test).value = "这是 Home 发送给 Main 的消息"
//        LiveDataBus.with<String>(Event_Type_Test).value = "这是 Home 发送给 Main 的消息"

        initPermission()
    }

    private fun initPermission() {
        PermissionUtils()
            .create()
            .activity(this)
            .permissions(permission_read_contacts, permission_camera)
            .callback(
                object : PermissionCallback{
                    override fun onAllGranted() {
                        ToastUtils.display(this@HomeActivity,"通过")
                    }

                    override fun onDeniedList(denied: List<String>) {
                        ToastUtils.display(this@HomeActivity,denied.toString())
                    }
                })
    }
}