package com.inqlab.laza.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("load_image_url")
fun loadImage(imageView: ImageView,url:String){
    imageView.loadImageUrl(url)
}

