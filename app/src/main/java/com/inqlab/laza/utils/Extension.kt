package com.inqlab.laza.utils

import android.view.View

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.INVISIBLE
}