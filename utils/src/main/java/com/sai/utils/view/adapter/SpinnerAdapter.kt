package com.sai.utils.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.widget.AppCompatTextView
import com.sai.utils.R

class SpinnerAdapter(private val context: Context, var data:MutableList<String>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val root = LayoutInflater.from(context)
            .inflate(R.layout.item_spinner, parent, false) as AppCompatTextView

        root.text = data[position]
        return root
    }

//    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val root: AppCompatTextView = LayoutInflater.from(context)
//            .inflate(R.layout.item_spinner, parent, false) as AppCompatTextView
//        root.text = data[position]
//        return root
//    }

    override fun getCount(): Int = data.size
    override fun getItem(position: Int): String = data[position]

    override fun getItemId(position: Int): Long = position.toLong()
}
