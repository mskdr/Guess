package com.muhammetkdr.guess.screens.title

import android.view.View

data class PlayButtonViewState(private val isPlayButtonVisible:Boolean) {
    fun getPlayButtonVisibility() = if(isPlayButtonVisible) View.VISIBLE else View.INVISIBLE

    companion object{
        fun initial() = PlayButtonViewState(false)
    }
}