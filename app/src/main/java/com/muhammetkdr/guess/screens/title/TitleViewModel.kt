package com.muhammetkdr.guess.screens.title

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class TitleViewModel : ViewModel() {

    val currentText = MutableLiveData<String>()
    //    private val _playButtonVisibility = MutableLiveData<Int>()
    val playButtonVisibility = Transformations.map(currentText) {
        PlayButtonViewState(it.length >= 5)
//        if (it.length >= 5) {
//            View.VISIBLE
//            PlayButtonViewState(true)
//        } else {
//            View.INVISIBLE
//            PlayButtonViewState(false)
//        }
    }
}