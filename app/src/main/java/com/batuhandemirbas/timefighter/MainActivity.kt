package com.batuhandemirbas.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    internal var score = 0
    internal var gameStarted = false

    internal lateinit var countDownTimer: CountDownTimer
    internal val initialCountDown: Long = 60000
    internal val countDownInterval: Long = 1000

    internal lateinit var tapMeButton: Button
    internal lateinit var scoreTextView: TextView
    internal lateinit var timeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tapMeButton = findViewById(R.id.tapMeButton)
        scoreTextView = findViewById(R.id.scoreTextView)
        timeTextView = findViewById(R.id.timeTextView)

        tapMeButton.setOnClickListener {
            incrementScore()
        }

        resetGame()
    }

    private fun resetGame() {
        score = 0
        scoreTextView.text = getString(R.string.score, score)
        timeTextView.text = getString(R.string.time, initialCountDown/1000)

        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval) {
            override fun onTick(p0: Long) {
                timeTextView.text = getString(R.string.time, p0/1000)
            }

            override fun onFinish() {
                endGame()
            }
        }
        gameStarted = false
    }

    private fun incrementScore() {
        if(!gameStarted) {
            startGame()
        }
        score++
        scoreTextView.text = getString(R.string.score, score)
    }

    private fun startGame() {
        countDownTimer.start()
        gameStarted = true
    }

    private fun endGame() {
        val toast = Toast.makeText(applicationContext, getString(R.string.gameover,score), Toast.LENGTH_SHORT)
        toast.show()
        resetGame()
    }
}