package com.example.helloworld

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_main.*
import kotlin.properties.Delegates

private const val GAME_DURATION: Long = 5000
private const val TIME_INTERVAL: Long = 1000
private const val INITIAL_SCORE = 0

class MainActivity : AppCompatActivity() {
    private var score: Int by Delegates.observable(INITIAL_SCORE) {
        _, _, new_score ->
        score_text.text = getString(R.string.current_score, new_score)
    }

    private var timeLeft: Long by Delegates.observable(GAME_DURATION) {
            _, _, new_time_left ->
        time_left_text.text = getString(R.string.time_left, new_time_left / 1000)
    }

    private val countDownTimer: CountDownTimer = object : CountDownTimer(GAME_DURATION, TIME_INTERVAL) {
        override fun onTick(millisUntilFinished: Long) {
            timeLeft = millisUntilFinished
        }

        override fun onFinish() {
            isGameOn = false
        }

    }

    private var isGameOn = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tap_button.setOnClickListener {
            startGameIfNeeded()
            incrementScore()
        }
    }

    override fun onStart() {
        super.onStart()

        resetGame()
    }

    private fun startGameIfNeeded() {
        if (!this.isGameOn) {
            countDownTimer.start()
            this.isGameOn = true
        }
    }

    private fun incrementScore() {
        if (this.isGameOn) {
            score += 1
        }
    }

    private fun resetGame() {
        score = INITIAL_SCORE
        timeLeft = GAME_DURATION
    }
}
