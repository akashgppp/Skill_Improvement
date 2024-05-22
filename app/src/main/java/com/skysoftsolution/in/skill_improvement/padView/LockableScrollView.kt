package com.skysoftsolution.`in`.skill_improvement.padView
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView

class LockableScrollView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ScrollView(context, attrs) {

    private var isScrollingEnabled = true

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return isScrollingEnabled && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return isScrollingEnabled && super.onInterceptTouchEvent(ev)
    }

    fun setScrollingEnabled(enabled: Boolean) {
        isScrollingEnabled = enabled
    }
}