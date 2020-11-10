package com.sai.tools

import android.content.Intent
import android.os.Bundle
import com.sai.utils.bese.BaseActivity
import com.sai.utils.event.LiveEventBus
import com.sai.utils.log.LogUtils
import com.sai.utils.socket.SocketUtils
import com.sai.utils.state_error
import com.sai.utils.toast.ToastUtils
import com.sai.utils.view.SaiEditView
import com.sai.utils.view.SaiSpinnerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : BaseActivity() {

    // https://jitpack.io/#xiaoerMr/Tools/v0.01

    private var socketUtils: SocketUtils? = null

    override fun getLayoutId(): Int = R.layout.activity_main


    override fun initView(savedInstanceState: Bundle?) {
//       ---- LiveEventBus示例 ----
        LiveEventBus.get().with(Event_Type_Test)
//        LiveDataBus.with<String>(Event_Type_Test)
            .observe(this, { ToastUtils.display(this, "---LiveDataBus--> $it") })

        button.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

//        LogUtils.e("RootPath = ${FileUtils.getRootPath(this)}")

//       ---- socket 示例 ----
//        lifecycleScope.launch(Dispatchers.IO) {
//             socketUtils = SocketUtils("192.168.0.105", 8090)
//
//            while (true) {
//                socketUtils!!.receive()?.let {
//                    LogUtils.e(it)
//                }
//            }
//        }
//
        send_socket.setOnClickListener {
//            lifecycleScope.launch(Dispatchers.IO) {
//                socketUtils?.send("iii")
//            }
            my_text.setText(my_edit.getInputText())
        }

        val items = mutableListOf("好像是的", "你不怕", "我怕", "你的", "还好", "苹果")
        my_spinner.setAdapterData(items, object : SaiSpinnerView.SpinnerItemSelectListener {
            override fun onItemSelect(
                selectPosition: Int,
                selectItem: String
            ) {
                LogUtils.e(selectItem)
            }
        })
        my_spinner.setDefSelectItem(4)

        my_text.setText("页面")
//        my_text.setText("mutableListOf(\"好像是的\", \"你不怕\", \"我怕\", \"你的\", \"还好\", \"苹果\")mutableListOf(\"好像是的\", \"你不怕\", \"我怕\", \"你的\", \"还好\", \"苹果\")mutableListOf(\"好像是的\", \"你不怕\", \"我怕\", \"你的\", \"还好\", \"苹果\")")
        my_edit.setInputListener(object : SaiEditView.InputListener {
            override fun inputText(text: String) {
                LogUtils.e(text)
            }
        })
        my_edit.setDefText("我是默认数据")
        my_edit_num.setInputListener(object : SaiEditView.InputListener {
            override fun inputText(text: String) {
                LogUtils.e(text)
            }
        })
    }

    override fun initData() {
//        setPageState(state_error)

        GlobalScope.launch {
            delay(5000)
            runOnUiThread(Runnable {
                hintPageState()
                setPageState(state_error)
            })
        }
    }
}