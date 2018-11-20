package com.ysn.mvvmsample.view.adapter

import android.databinding.BindingAdapter
import android.view.View

@BindingAdapter("visibleGone")
fun View.showHide(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}
