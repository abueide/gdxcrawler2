package com.abysl.gdxcrawler.lwjgl3

import com.abysl.gdxcrawler.Main
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration

object Lwjgl3Launcher {
    @JvmStatic
    fun main(args: Array<String>) {
        createApplication()
    }

    private fun createApplication(): Lwjgl3Application {
        return Lwjgl3Application(Main(), defaultConfiguration)
    }

    private val defaultConfiguration: Lwjgl3ApplicationConfiguration
        private get() {
            val config = Lwjgl3ApplicationConfiguration()
            config.setTitle("gdxcrawler2")
//            config.setWindowedMode(1280, 720)
            config.setWindowedMode(1561, 1057)
//            config.setWindowedMode(1280, 720)
//            config.setWindowedMode(320, 180)

//            config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode())
            config.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png")
            return config
        }
}