package com.sai.utils.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.FrameLayout
import com.sai.utils.R
import com.sai.utils.view.adapter.SpinnerAdapter
import kotlinx.android.synthetic.main.sai_spinner_view.view.*
import android.widget.AdapterView.OnItemSelectedListener

class SaiSpinnerView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var adapter: SpinnerAdapter? = null
    private var inflater: View? = null
    var selectedItem: String? = null
    var listener: SpinnerItemSelectListener? = null

    private fun initView(context: Context, attrs: AttributeSet?) {
        //获取布局
        inflater = LayoutInflater.from(context).inflate(
            R.layout.sai_spinner_view,
            this
        )

        //获取参数
        val obtain = context.obtainStyledAttributes(attrs, R.styleable.sai_spinner)

        val title = obtain.getString(R.styleable.sai_spinner_sai_sp_title)

        obtain.recycle()

        inflater!!.SpinnerTitle.text = title
    }

    fun setDefSelectItem(position: Int) {
        inflater!!.SpinnerView.setSelection(position, true)
    }

    fun setSpinnerItemSelectListener(listener: SpinnerItemSelectListener) {
        this.listener = listener
    }

    fun setAdapterData(data: MutableList<String>) {
        if (adapter == null) {
            adapter = SpinnerAdapter(context, data)
        } else {
            adapter!!.data = data
        }
        inflater!!.SpinnerView.adapter = adapter
        inflater!!.SpinnerView.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedItem = data[position]
                if (listener != null) {
                    listener!!.onItemSelect(position, selectedItem!!)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedItem = data[0]
            }
        }

    }

    fun setAdapterData(data: MutableList<String>, listener: SpinnerItemSelectListener) {
        if (adapter == null) {
            adapter = SpinnerAdapter(context, data)
        } else {
            adapter!!.data = data
        }
        inflater!!.SpinnerView.adapter = adapter
        inflater!!.SpinnerView.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                listener.onItemSelect(position, data[position])
                selectedItem = data[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedItem = data[0]
            }
        }
    }

    init {
        initView(context, attrs)
    }

    interface SpinnerItemSelectListener {
        fun onItemSelect(selectPosition: Int, selectItem: String)
    }
}
