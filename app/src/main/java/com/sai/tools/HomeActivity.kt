package com.sai.tools

import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.date.dayOfMonth
import com.afollestad.date.month
import com.afollestad.date.year
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BasicGridItem
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.bottomsheets.gridItems
import com.afollestad.materialdialogs.callbacks.onCancel
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.afollestad.materialdialogs.callbacks.onPreShow
import com.afollestad.materialdialogs.callbacks.onShow
import com.afollestad.materialdialogs.datetime.dateTimePicker
import com.afollestad.materialdialogs.input.input
import com.afollestad.materialdialogs.list.listItemsMultiChoice
import com.afollestad.materialdialogs.list.listItemsSingleChoice
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
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.AppTheme_CustomMaterialDialog)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        LiveEventBus.get().with(Event_Type_Test).value = "这是 Home 发送给 Main 的消息"
//        LiveDataBus.with<String>(Event_Type_Test).value = "这是 Home 发送给 Main 的消息"

//        initPermission()

        val msg =  "dialog msg dialog msg dialog msg dialog msg dialog msg dialog msg dialog msg dialog msg"

        button2.setOnClickListener {
            GlobalScope.launch {

                HttpRequest().getAllTask(object : RequestCallback<ResTask> {
                    override fun onSuccess(data: ResTask) {
                        loge("onSuccess -> ${Thread.currentThread().name}")
                        runOnUiThread {
                            text.text = data.toString()
                        }
                    }

                    override fun onError(errorMsg: String) {
                        loge("onError -> ${Thread.currentThread().name}")
                        runOnUiThread { text.text = errorMsg }
                    }
                })

            }
        }

        //基础dialog 按钮不写则不显示该按钮
        dialog.setOnClickListener {
            MaterialDialog(this)
                .cancelOnTouchOutside(false)
                .negativeButton(text = "不确定") { toast("cancal") }
                .positiveButton { toast("ok") }
                .show {
                    icon(R.drawable.ic_state_empty)
                    title(text = "title")
                    message(text = msg)
                    onDismiss { toast("关闭") }
                    onPreShow { toast("显示前") }
                    onShow { toast("显示") }
                    onCancel { toast("取消") }
                }
        }
        //单选列表  将listItemsSingleChoice 替换为 listItems 是 列表展示
        val  listItem = listOf("第一", "第二", "第三", "第四", "第五", "第六")
        dialogList.setOnClickListener {
            MaterialDialog(this)
                .show {
                    icon(R.drawable.ic_state_empty)
                    title(text = "单选")
                    message(text = "这里是单选")
                    listItemsSingleChoice(items = listItem, initialSelection = 2){ dialog, index, text -> toast(
                        "选择 $text"
                    )
                    }
                    cancelOnTouchOutside(false)
                    negativeButton()
                    positiveButton()
                }
        }
        dialogMultiple.setOnClickListener {
            MaterialDialog(this)
                .show {
                    icon(R.drawable.ic_state_empty)
                    title(text = "多选")
                    message(text = "这里是多选")
                    listItemsMultiChoice(items = listItem, initialSelection = intArrayOf(1, 3)){ dialog, indices, items -> toast(
                        "选择 ${items.toString()}"
                    )
                    }
                    cancelOnTouchOutside(false)
                    negativeButton()
                    positiveButton()
                }
        }

        val bottm = listOf(
            BasicGridItem(R.drawable.ic_state_empty, "One0"),
            BasicGridItem(R.drawable.ic_state_empty, "One1"),
            BasicGridItem(R.drawable.ic_state_empty, "One2"),
            BasicGridItem(R.drawable.ic_state_empty, "One3"),
            BasicGridItem(R.drawable.ic_state_empty, "One4"),
//            BottomSheet(LayoutMode.WRAP_CONTENT))
        )

        dialogBottom.setOnClickListener {
            MaterialDialog(this, BottomSheet())//LayoutMode.WRAP_CONTENT
                .show {
                    icon(R.drawable.ic_state_empty)
                    title(text = "底部")
                    message(text = "这里是多选")
                    gridItems(bottm){ _, index, item->
                        toast(item.title)
                    }
                }
        }
        dialogData.setOnClickListener {
            MaterialDialog(this)//LayoutMode.WRAP_CONTENT
                .show {
                    icon(R.drawable.ic_state_empty)
                    title(text = "日期")
                    message(text = "这里是选日期")
                    dateTimePicker(
                        requireFutureDateTime = false,
                        show24HoursView = false,
                    ){ _, dataTime->
                        val sdf = SimpleDateFormat("yyyy年MM月dd日 HH:mm")
                        toast("${sdf.format(dataTime.time)} ")
                    }
                }
        }
        dialogInput.setOnClickListener {
            MaterialDialog(this)//LayoutMode.WRAP_CONTENT
                .show {
                    icon(R.drawable.ic_state_empty)
                    title(text = "输入")
                    message(text = "这里是输入")
//                    positiveButton(text = "确定")
                    input(
                        inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
                        hint = "this is hint",
                        maxLength = 6,
                        waitForPositiveButton = true,// 是否关闭监听输入过程的回调
                        allowEmpty = true
                    ) { _, charSequence -> toast(charSequence.toString()) }
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
                        toast(denied.toString())
                    }
                })
    }
}