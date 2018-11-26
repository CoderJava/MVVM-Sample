package com.ysn.mvvmsample.extensions

import android.databinding.BindingAdapter
import android.view.View

@BindingAdapter("visibleGone")
fun View.visibleGone(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}