package com.sai.utils.img

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sai.utils.R

class ImageUtils {
    private val options by lazy {
        val options = RequestOptions()
            .placeholder(R.drawable.ic_state_empty)
            .error(R.drawable.ic_state_error)
            .fitCenter()
        options
    }

    fun loadImage(fragment: Fragment, imgUrl: String, imageView: ImageView) {
        Glide.with(fragment)
            .load(imgUrl)
            .apply(options)
            .into(imageView)
    }
    fun loadImage(activity: AppCompatActivity, imgUrl: String, imageView: ImageView) {
        Glide.with(activity)
            .load(imgUrl)
            .apply(options)
            .into(imageView)
    }

    fun clearImage(fragment: Fragment, imageView: ImageView) {
        Glide.with(fragment).clear(imageView)
    }

}