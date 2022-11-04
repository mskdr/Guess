package com.muhammetkdr.guess.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel (private val currentScore: Int) : ViewModel(){

    private val _onPlayAgainLiveData = MutableLiveData<Boolean>()
    val onPlayAgainLiveData : LiveData<Boolean> get() = _onPlayAgainLiveData



    val score = currentScore

    fun onPlayAgain() {
        _onPlayAgainLiveData.value = true
    }

    fun finishPlayAgainEvent(){
        _onPlayAgainLiveData.value = false
    }
}