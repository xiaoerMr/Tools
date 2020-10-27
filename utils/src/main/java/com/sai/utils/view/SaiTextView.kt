package com.sai.utils.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.sai.utils.R
import kotlinx.android.synthetic.main.sai_spinner_view.view.*
import kotlinx.android.synthetic.main.sai_text_view.view.*

class SaiTextView : FrameLayout {

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, attrs)
    }

    private var inflater: View? = null


    private fun initView(context: Context, attrs: AttributeSet?) {
        //获取布局
        inflater = LayoutInflater.from(context).inflate(
            R.layout.sai_text_view,
            this
        )

        //获取参数
        val obtain = context.obtainStyledAttributes(attrs, R.styleable.sai_text)
        val title = obtain.getString(R.styleable.sai_text_sai_tt_title)

        obtain.recycle()

        inflater!!.TextTitle.text = title
    }

    fun setText(text: String) {
        inflater!!.TextText.text = text
    }
}