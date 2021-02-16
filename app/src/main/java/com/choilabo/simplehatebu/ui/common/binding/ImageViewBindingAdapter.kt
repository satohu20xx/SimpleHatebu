package com.choilabo.simplehatebu.ui.common.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Created by sato_shinichiro on 2020/09/30
 */
@BindingAdapter("imageUrl")
fun ImageView.load(imageUrl: String?) {
    Glide.with(context).also {
        it.clear(this)
        it.load(imageUrl)
            .into(this)
    }
}