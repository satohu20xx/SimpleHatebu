package com.choilabo.simplehatebu.ui.common.binding

import android.view.View
import androidx.annotation.ColorRes
import androidx.databinding.BindingAdapter

/**
 * Created by sato_shinichiro on 2020/10/01
 */
@BindingAdapter("bgColorRes")
fun View.setBgColorRes(@ColorRes bgColorRes: Int) {
    setBackgroundResource(bgColorRes)
}