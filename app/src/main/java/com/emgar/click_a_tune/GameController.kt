package com.emgar.click_a_tune

import MusicPlayer
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.random.Random

// handle game logic, such as timing and score management

class GameController(
    private val scoreView: TextView,
    private val musicPlayer: MusicPlayer,
    private val jump: Jump,
    private val beatTimes: List<Long>,
    private val timeView: TextView// Times in ms when puck should move
) : CoroutineScope by MainScope() {
    private var score = 0
    private var nextBeatIndex = 0
    private var streak = 0
    private var wasTapped = false

    fun startGame() {
        beatTimes
        musicPlayer.startMusic()
        launch {
            while (isActive) {
                val currentPosition = musicPlayer.getCurrentPosition()
                if (nextBeatIndex < beatTimes.size && currentPosition >= beatTimes[nextBeatIndex]) {
                    movePuckToBeat()
                    // Reset streak if the puck was not tapped on time for this beat
                    if (!wasTapped) {
                        streak = 0
                        timeView.post { timeView.text = "Multiplier: x$streak" }
                    }
                    nextBeatIndex++
                }

                scoreView.post { scoreView.text = "Score: $score" }
                timeView.post { timeView.text = "Multiplier: x$streak"}
                    delay(50) // Check every 50 ms
            }
        }
    }

    private fun movePuckToBeat() {
        // Move the puck or make it clickable/visible
        jump.start() // places it randomly
        //was_tapped = false
        timeView.post { timeView.text = "Multiplier: x$streak" }
    }

    private fun updateScore() {
        score += 10 * streak  // Increment score by 10 times the current streak
        scoreView.post { scoreView.text = "Score: $score" }
        timeView.post { timeView.text = "Multiplier: x$streak"}
    }



    fun puckTapped() {
            streak += 1
            wasTapped = true
            updateScore()
            jump.invis(boolean = true)

        scoreView.post { scoreView.text = "Score: $score" }
        timeView.post { timeView.text = "Multiplier: x$streak"}
    }

    fun stopGame() {
        cancel()
        musicPlayer.stopMusic()
    }
    private fun generateBeatTimes(): List<Long> {
        return listOf(0, 2000, 4000, 6000, 8000, 10000, 12000, 14000, 16000, 18000, 20000, 22000,
            24000, 25500, 27000, 28500, 30000, 31500, 33000, 34500, 36000, 37500, 39000, 40500, 42000, 43500,
            45000, 46000, 47000, 48000, 49000, 50000, 51000, 52000, 53000, 54000, 55000,
            56000, 56500, 57000, 57500, 58000, 58500, 59000, 59500, 60000, 60500, 61000, 61500,
            62000, 62400, 62800, 63200, 63600, 64000, 64400, 64800, 65200, 65600, 66000, 66400,
            66800, 67200, 67600, 68000, 68400, 68800, 69200, 69600, 70000, 70400, 70800, 71200,
            71600, 72000, 72400, 72800, 73200, 73600, 74000, 74400, 74800, 75200, 75600, 76000,
            76400, 76800, 77200, 77600, 78000, 78400, 78800, 79200, 79600, 80000, 80400, 80800,
            81200, 81600, 82000, 82400, 82800, 83200, 83600, 84000, 84400, 84800, 85200, 85600,
            86000, 86400, 86800, 87200, 87600, 88000, 88400, 88800, 89200, 89600, 90000, 90400,
            90800, 91200, 91600, 92000, 92400, 92800, 93200, 93600, 94000, 94400, 94800, 95200,
            95600, 96000, 96400, 96800, 97200, 97600, 98000, 98400, 98800, 99200, 99600, 100000,
            100400, 100800, 101200, 101600, 102000, 102400, 102800, 103200, 103600, 104000, 104400, 104800,
            105200, 105600, 106000, 106400, 106800, 107200, 107600, 108000, 108400, 108800, 109200, 109600,
            110000, 110400, 110800, 111200, 111600, 112000, 112400, 112800, 113200, 113600, 114000, 114400,
            114800, 115200, 115600, 116000)
    }
}
