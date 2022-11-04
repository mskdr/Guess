package com.muhammetkdr.guess.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {


    private var timer : CountDownTimer

    private val _wordLiveData = MutableLiveData<String>()
    val wordLiveData : LiveData<String> get() = _wordLiveData

    private val _scoreLiveData = MutableLiveData<Int>()
    val scoreLiveData : LiveData<Int> get() = _scoreLiveData

    private val _gameFinishEventLiveData = MutableLiveData<Boolean>()
    val gameFinishEventLiveData : LiveData<Boolean> get() = _gameFinishEventLiveData

    private val currentTimeLiveData = MutableLiveData<Long>()
//    val currentTimeLiveData: LiveData<Long> get() = _currentTimeLiveData

    val mappedCurrentTimeLiveData = Transformations.map(currentTimeLiveData){
        DateUtils.formatElapsedTime(it)
    }


    private lateinit var wordList : MutableList<String>

    init {
        _scoreLiveData.value = 0
        resetList()
        nextWord()

       timer = object :CountDownTimer(START_TIME, ONE_SECOND){

            override fun onTick(millisUntilFinished: Long) {
                currentTimeLiveData.value = millisUntilFinished / ONE_SECOND
            }

            override fun onFinish() {
                currentTimeLiveData.value = DONE
            }
        }
        timer.start()

    }

//    fun onNoTimeLeft(){
//        if(currentTimeLiveData.value == DONE){
//
//        }
//    }
//

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "champe",
            "small",
            "soup",
            "calender",
            "sad",
            "desk",
            "guitar",
            "home",
            "jelly",
            "car",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    private fun nextWord() {
        if(wordList.isNotEmpty()){
//            word = wordList.removeAt(0)
            _wordLiveData.value = wordList.removeAt(0)
        }else{
            onFinishGame()
        }
    }

    fun onSkip() {
        _scoreLiveData.value = _scoreLiveData.value?.minus(1)
//        score--
        nextWord()
    }

    fun onCorrect() {
        _scoreLiveData.value = _scoreLiveData.value?.plus(1)
//        score++
        nextWord()    }

    fun onFinishGame() {
        _gameFinishEventLiveData.value = true
    }

    fun disableGameFinishEvent() {
        _gameFinishEventLiveData.value = false
    }

    companion object{
        private const val START_TIME = 60000L
        private const val ONE_SECOND = 1000L
        private const val DONE = 0L

    }
}