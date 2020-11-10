package com.sai.utils.view

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import com.sai.utils.R
import kotlinx.android.synthetic.main.sai_edit_view.view.*


class SaiEditView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var inflater: View? = null


    private fun initView(context: Context, attrs: AttributeSet?) {
        //获取布局
        inflater = LayoutInflater.from(context).inflate(
            R.layout.sai_edit_view,
            this
        )

        //获取参数
        val obtain = context.obtainStyledAttributes(attrs, R.styleable.sai_edit)
        val title = obtain.getString(R.styleable.sai_edit_sai_ev_title)
        val hint = obtain.getString(R.styleable.sai_edit_sai_ev_hint)
        val type = obtain.getInt(R.styleable.sai_edit_sai_ev_type, 0)

        // 类型：0：默认 文本；  1： number
        if (type == 1) {
            inflater!!.EditText.inputType =
                EditorInfo.TYPE_CLASS_NUMBER or EditorInfo.TYPE_NUMBER_FLAG_DECIMAL
        }

        obtain.recycle()

        inflater!!.EditTitle.text = title
        inflater!!.EditText.hint = hint

    }

    init {
        initView(context, attrs)
    }

    fun setInputListener(listener: InputListener) {
        inflater!!.EditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                listener.inputText(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    fun getInputText() = inflater!!.EditText.text.toString().trim()

    fun setDefText(text: String){
        inflater!!.EditText.setText(text)
    }
    interface InputListener {
        fun inputText(text: String)
    }
}