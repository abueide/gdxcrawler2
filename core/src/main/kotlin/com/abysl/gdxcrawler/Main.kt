package com.abysl.gdxcrawler

import com.badlogic.gdx.Game

class Main : Game() {
    override fun create() {
        setScreen(GameScreen())
    }
}