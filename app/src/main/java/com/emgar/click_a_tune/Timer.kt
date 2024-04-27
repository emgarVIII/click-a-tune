package com.emgar.click_a_tune

import android.graphics.Color
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.coroutines.coroutineContext

class Timer(private var textView: TextView) {
    private var endMillis = 0L // Only read, not Atomic

    fun millisLeft(): Long {return endMillis - System.currentTimeMillis()}

    suspend fun timerCo(durationMillis: Long) {
        endMillis = System.currentTimeMillis() + durationMillis
        var currentMillis = System.currentTimeMillis()
        // End color of replayButton is  red
        val delayMillis = 100L // Time step for updates
        // XML button
        textView.setBackgroundColor(Color.WHITE)

        while (coroutineContext.isActive
            && (endMillis > currentMillis)) {
            // XML TextView
            textView.text = String.format(
                "%1.1f",
                (endMillis - currentMillis) / 1000.0f
            )
            val scaleFactor = (endMillis - currentMillis).toFloat() / durationMillis.toFloat()
            textView.setBackgroundColor(
                Color.rgb(
                    255,
                    (255 * scaleFactor).toInt(),
                    (255 * scaleFactor).toInt()
                )
            )
            delay(delayMillis)
            currentMillis = System.currentTimeMillis()
        }
        textView.text = "0" // 0 when done
    }
}