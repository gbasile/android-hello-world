package com.example.helloworld

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var model: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProviders.of(this).get(GameViewModel::class.java)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            this.viewModel = model
            setLifecycleOwner(this@MainActivity)
        }

        model.finalScore.observeNonNull(this) { finalScore ->
            val toast = Toast.makeText(this@MainActivity, getString(R.string.final_score, finalScore), Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    override fun onStart() {
        super.onStart()

        model.reset()
    }
}

