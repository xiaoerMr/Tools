package com.sai.tools

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sai.utils.event.LiveDataBus
import com.sai.utils.event.LiveEventBus
import com.sai.utils.file.FileUtils
import com.sai.utils.log.LogUtils
import com.sai.utils.toast.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LiveEventBus.get().with(Event_Type_Test)
//        LiveDataBus.with<String>(Event_Type_Test)
            .observe(this, { ToastUtils.display(this, "---LiveDataBus--> $it") })

        LogUtils.e("RootPath = ${FileUtils.getRootPath(this)}")

        button.setOnClickListener { startActivity(Intent(this, HomeActivity::class.java)) }
    }
}