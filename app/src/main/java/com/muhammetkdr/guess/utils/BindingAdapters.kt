package com.muhammetkdr.guess.utils

import android.widget.Button
import androidx.databinding.BindingAdapter

@BindingAdapter("playButtonVisibility")
fun Button.setPlayButtonVisibilitiy(playButtonVisibility : Int){
    this.visibility = playButtonVisibility
}