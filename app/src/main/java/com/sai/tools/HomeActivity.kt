package com.sai.tools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sai.utils.event.LiveDataBus
import com.sai.utils.event.LiveEventBus

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        LiveEventBus.get().with(Event_Type_Test).value = "这是 Home 发送给 Main 的消息"
//        LiveDataBus.with<String>(Event_Type_Test).value = "这是 Home 发送给 Main 的消息"

    }
}