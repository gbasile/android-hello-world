package com.example.helloworld

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.CountDownTimer

private const val INITIAL_SCORE = 0
private const val GAME_DURATION: Long = 5000
private const val GAME_COUNTDOWN_INTERVAL: Long = 1000

class GameViewModel: ViewModel() {
    val score = MutableLiveData<Int>()
    val timeLeft = MutableLiveData<Long>()
    private var isGameOn = false

    private val countDownTimer: CountDownTimer = object : CountDownTimer(GAME_DURATION, GAME_COUNTDOWN_INTERVAL) {
        override fun onTick(millisUntilFinished: Long) {
            timeLeft.value = millisUntilFinished
        }

        override fun onFinish() {
            isGameOn = false
        }
    }

    fun incrementScore() {
        startGameIfNeeded()
        val currentScore = score.value ?: INITIAL_SCORE

        score.value = currentScore + 1
    }

    fun reset() {
        score.value = 0
        timeLeft.value = GAME_DURATION
        isGameOn = false
    }

    private fun startGameIfNeeded() {
        if (!isGameOn) {
            reset()
            countDownTimer.start()
            isGameOn = true
        }
    }
}