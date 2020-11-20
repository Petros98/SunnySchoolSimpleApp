package com.sunnyschool.simpleapp

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImage(path: String){
    Glide.with(this)
        .load(path)
        .error(R.drawable.ic_baseline_broken_image_24)
        .placeholder(R.drawable.loading_anim)
        .into(this)
}