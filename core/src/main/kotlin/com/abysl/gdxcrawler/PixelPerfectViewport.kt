package com.abysl.gdxcrawler

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.viewport.FitViewport


class PixelPerfectViewport(worldWidth: Float, worldHeight: Float) : FitViewport(worldWidth, worldHeight) {

    override fun update(screenWidth: Int, screenHeight: Int, centerCamera: Boolean) {
        // get the min screen/world rate from width and height
        val wRate = screenWidth / worldWidth
        val hRate = screenHeight / worldHeight
        val rate = minOf(wRate, hRate)

        // round it down and limit to one
        val iRate =  maxOf(1, MathUtils.floor(rate))

        // compute rounded viewport dimension
        val viewportWidth = worldWidth.toInt() * iRate
        val viewportHeight = worldHeight.toInt() * iRate

        // Center.
        setScreenBounds((screenWidth - viewportWidth) / 2, (screenHeight - viewportHeight) / 2, viewportWidth, viewportHeight)
        apply(centerCamera)
    }
}