package com.emgar.click_a_tune

import android.graphics.Color
import android.util.Log
import android.view.View
import kotlin.random.Random

class Border(private val borders: List<View>
) {
    private var minX = 0
    private var maxX = 0
    private var minY = 0
    private var maxY = 0
    private var random = Random(2)

    enum class Type(val i: Int) {
        T/*OP*/(0),
        B/*OTTOM*/(1),
        S/*TART*/(2),
        E/*ND*/(3);
        companion object {
            private val values = values()
            fun getByInt(value: Int) = values.firstOrNull { it.i == value }
        }
    }

    init {
        Log.d("MinX MaxX MinY MaxY", String.format("%d %d %d %d", minX, maxX, minY, maxY))
        minX = (borders[2].x + borders[2].width).toInt()
        maxX = borders[3].x.toInt()
        minY = (borders[0].y + borders[0].height).toInt()
        maxY = borders[1].y.toInt()
        Log.d("MinX MaxX MinY MaxY", String.format("%d %d %d %d", minX, maxX, minY, maxY))
    }

    // Unused, but nice to have around
//    private fun toPixels(dp: Float) : Int {
//        return TypedValue.applyDimension(
//            TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics).toInt()
//    }
    fun minX() : Int { return minX}
    fun maxX() : Int { return maxX}
    fun minY() : Int { return minY}
    fun maxY() : Int { return maxY}

    fun randomX(puckWidth: Int) : Float {
        return random.nextInt(minX, maxX-puckWidth).toFloat()
    }
    fun randomY(puckHeight: Int) : Float {
        return random.nextInt(minY, maxY - puckHeight).toFloat()
    }

    fun resetBorderColors() {
        // Reset frame
        borders.forEach { it.setBackgroundColor(Color.BLACK) }
    }
}