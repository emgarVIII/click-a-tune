package com.emgar.click_a_tune

import android.widget.TextView
import kotlinx.coroutines.*
// handle game logic, such as timing and score management

class GameController(
    private val scoreView: TextView,
    private val updateInterval: Long = 2000L // Default interval
) : CoroutineScope by MainScope() {
    private var score = 0

    fun startGame() {
        launch {
            while (isActive) {
                delay(updateInterval)
                updateScore()
            }
        }
    }

    private fun updateScore() {
        score += 10
        scoreView.post { scoreView.text = "Score: $score" }
    }

    fun stopGame() {
        cancel()
    }
}
