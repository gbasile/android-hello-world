package com.example.helloworld

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var model: GameViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProviders.of(this).get(GameViewModel::class.java)
        setContentView(R.layout.activity_main)

        tap_button.setOnClickListener {
            model.incrementScore()
        }

        model.timeLeft.observeNonNull(this) { timeLeft ->
            time_left_text.text = getString(R.string.time_left, timeLeft / 1000)
        }

        model.score.observeNonNull(this) { score ->
            score_text.text = getString(R.string.current_score, score)
        }
    }

    override fun onStart() {
        super.onStart()

        model.reset()
    }
}

