package com.sai.tools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sai.tools.http.HttpRequest
import com.sai.tools.http.ResTask
import com.sai.utils.http.RequestCallback
import com.sai.utils.loge
import com.sai.utils.permission.PermissionCallback
import com.sai.utils.permission.PermissionUtils
import com.sai.utils.permission_camera
import com.sai.utils.permission_read_contacts
import com.sai.utils.toast
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        LiveEventBus.get().with(Event_Type_Test).value = "这是 Home 发送给 Main 的消息"
//        LiveDataBus.with<String>(Event_Type_Test).value = "这是 Home 发送给 Main 的消息"

//        initPermission()

        button2.setOnClickListener {
            GlobalScope.launch{

                HttpRequest().getAllTask(object : RequestCallback<ResTask> {
                    override fun onSuccess(data: ResTask) {
                        loge("onSuccess -> ${Thread.currentThread().name}")
                        runOnUiThread {
                            text.text = data.toString()
                    }}

                    override fun onError(errorMsg: String) {
                        loge("onError -> ${Thread.currentThread().name}")
                        runOnUiThread{ text.text = errorMsg}
                    }
                })

            }
        }
    }

    private fun initPermission() {
        PermissionUtils()
            .create()
            .activity(this)
            .permissions(permission_read_contacts, permission_camera)
            .callback(
                object : PermissionCallback {
                    override fun onAllGranted() {
//                        ToastUtils.display(this@HomeActivity, )
                        toast("通过")
                    }

                    override fun onDeniedList(denied: List<String>) {
                        toast( denied.toString())
                    }
                })
    }
}