package com.inqlab.laza.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.inqlab.laza.R

fun ImageView.loadImageUrl(url : String) {
    Glide.with(this).load(url).placeholder(R.drawable.placeholder)
        .into(this)
}
