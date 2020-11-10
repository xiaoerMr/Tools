package com.sai.utils.bese

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.sai.utils.R
import com.sai.utils.state_empty
import com.sai.utils.state_error
import com.sai.utils.state_loading
import kotlinx.android.synthetic.main.layout_activity_base.view.*

abstract class BaseActivity : AppCompatActivity() {

    private var statePage: View? = null
    private val loadingAnimation: RotateAnimation by lazy {
//       val objectAnimator= ObjectAnimator.ofFloat(statePage!!.stateImg,"rotation", 0f,360f)
//        objectAnimator.duration = 2000
//        objectAnimator.repeatMode = ValueAnimator.RESTART
//        objectAnimator.repeatCount = -1
//        objectAnimator

        val a = RotateAnimation(
            0f,
            359f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        a.duration = 2000
        a.repeatMode = ValueAnimator.RESTART
        a.repeatCount = -1
        a
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        statePage = View.inflate(this, R.layout.layout_activity_base, null)
        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        layoutParams.setMargins(
            0, 0,
//            ConvertUtils.dp2px(
//                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 70f else 55f),
            0, 0
        )

        setContentView(getLayoutId())
        addContentView(statePage, layoutParams)

        setPageState(state_loading)

        initView(savedInstanceState)
        initData()
    }

    fun hintPageState() {
        statePage?.let {
            if (it.visibility != View.GONE) {
                it.visibility = View.GONE
                loadingAnimation.cancel()
//                loadingAnimation.isRunning
            }
        }
    }

    public fun setPageState(state: Int) {
        statePage?.let {
            if (it.visibility == View.GONE) {
                it.visibility = View.VISIBLE
            }

            when (state) {
                state_empty -> {
                    loadingAnimation.cancel()
                    it.stateName.text = "暂时没有数据了"
                    it.stateImg.background = resources.getDrawable(R.drawable.ic_state_empty)
                }

                state_error -> {
                    loadingAnimation.cancel()
                    it.stateName.text = "加载错误"
                    it.stateImg.background = resources.getDrawable(R.drawable.ic_state_error)
                }

                state_loading -> {
                    it.stateName.text = "加载中"
                    it.stateImg.background = resources.getDrawable(R.drawable.ic_state_loading)
                    it.stateImg.animation = loadingAnimation
                    loadingAnimation.start()
                }
            }
        }
    }

    abstract fun getLayoutId(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun initData()

}