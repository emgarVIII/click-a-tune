package com.emgar.click_a_tune

import android.content.res.Resources
import android.view.View
import java.time.OffsetDateTime
import android.util.Log

class Jump(private val puck: View,
           private val border: Border
) {
    // XXX remember some X and Y values and any other state
    private val positions = listOf(
        Pair(border.minX(), border.minY()), // top left doesn't change
        Pair(border.maxX() - dpToPx(50), border.minY()), // top right, subtract 50dp from x
        Pair(border.maxX() - dpToPx(50), border.maxY() - dpToPx(50)), // bottom right, subtract 50dp from both x and y
        Pair(border.minX(), border.maxY() - dpToPx(50))  // bottom left, subtract 50dp from y
    )

    private fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    private var currentPositionIndex = 0

    private fun placePuck() {
        // XXX Write me
        val position = positions[currentPositionIndex]

        puck.x = position.first.toFloat()
        puck.y = position.second.toFloat()

        Log.d("Jump", "Placing puck at: x=${puck.x}, y=${puck.y}")

        currentPositionIndex = (currentPositionIndex + 1) % positions.size
    }

    init {
        puck.setOnClickListener { placePuck() }
    }

    fun start() {
        puck.visibility = View.VISIBLE
        puck.isClickable = true
        // XXX Write me
        border.resetBorderColors()
        currentPositionIndex = 0 // Start from the top left
        placePuck()
    }
    fun finish() {
        // XXX Write me
        // mimic demo behavior - it pertains through gamemodes
        //puck.visibility = View.INVISIBLE
        puck.isClickable = false

    }
}