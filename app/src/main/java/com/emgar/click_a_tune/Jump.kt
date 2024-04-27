package com.emgar.click_a_tune

import android.content.res.Resources
import android.view.View
import java.time.OffsetDateTime
import android.util.Log
import kotlin.random.Random

class Jump(private val puck: View,
           private val border: Border
) {
    private val puckMinX = border.minX().toFloat()
    private val puckMaxX = (border.maxX() - puck.width).toFloat()
    private val puckMinY= border.minY().toFloat()
    private val puckMaxY = (border.maxY() - puck.height).toFloat()

    private fun placePuck() {
        Log.d("puckMaxX", "$puckMaxX")
        puck.x = Random.nextFloat() * (puckMaxX - puckMinX) + puckMinX
        puck.y = Random.nextFloat() * (puckMaxY - puckMinY) + puckMinY
        Log.d("Jump", "Puck position - X: $puck.x, Y: $puck.y")

        puck.visibility = View.VISIBLE
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    private var currentPositionIndex = 0

    init {
        puck.setOnClickListener { placePuck() }
    }

    fun start() {
        puck.visibility = View.VISIBLE
        puck.isClickable = true
        // XXX Write me
        Log.d("Jump", "puckMinX: $puckMinX, puckMaxX: $puckMaxX, puckMinY: $puckMinY, puckMaxY: $puckMaxY")
        border.resetBorderColors()
        placePuck()
    }

    fun invis(boolean: Boolean){
        if (boolean){
            puck.visibility = View.INVISIBLE
            puck.isClickable = false
        }else{
            puck.visibility = View.VISIBLE
            puck.isClickable = true
        }


    }

    fun finish() {
        // XXX Write me
        // mimic demo behavior - it pertains through gamemodes
        //puck.visibility = View.INVISIBLE
        puck.isClickable = false
    }
}