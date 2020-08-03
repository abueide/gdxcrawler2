package com.abysl.gdxcrawler

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Graphics
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import ktx.assets.loadOnDemand
import ktx.graphics.use
import java.lang.Math.round
import kotlin.math.abs
import kotlin.math.roundToInt

class GameScreen : Screen {
    val batch: SpriteBatch = SpriteBatch()
    val test: Sprite = Sprite(Texture("default.png"))
    val viewport: PixelPerfectViewport = PixelPerfectViewport(320f, 180f)
    val camera: OrthographicCamera = OrthographicCamera(20f, 11.25f)
    val assets = AssetManager().also { it.setLoader(TiledMap::class.java, TmxMapLoader()) }
    val map: TiledMap = assets.loadOnDemand<TiledMap>("Overworld.tmx").asset
    val mapRenderer = OrthogonalTiledMapRenderer(map, 1 / 16f)
    override fun show() {
        test.setSize(1f, 1f)
        test.setPosition(21f, 19f)
        mapRenderer.unitScale
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.projectionMatrix = camera.combined
        handleInput(delta)
        camera.position.set(test.x, test.y, 0f)
        camera.update()
        mapRenderer.render()
        mapRenderer.setView(camera)
        batch.use {
            test.draw(it)
        }
    }


    val tileSize = 16.0
    val baseWidth = 20.0
    val baseHeight = 11.25
    override fun resize(width: Int, height: Int) {
        val widthScale = (width.toFloat() / (baseWidth * tileSize)).roundToInt().coerceAtLeast(1)
        val heightScale = (height.toFloat() / (baseHeight * tileSize)).roundToInt().coerceAtLeast(1)
        val scale: Int = maxOf(widthScale, heightScale)
        val tileFactor: Double = scale.toDouble() * tileSize
        val widthMod: Double = (width - (baseWidth * tileFactor)) / tileFactor //- 0.01
        val heightMod: Double = (height.toFloat() - (baseHeight * tileFactor)) / tileFactor // - 0.01
        println("$width, $height")
        camera.viewportWidth = (baseWidth + widthMod).toFloat()
        camera.viewportHeight = (baseHeight + heightMod).toFloat()
    }

    override fun pause() {
        // Invoked when your application is paused.
    }

    override fun resume() {
        // Invoked when your application is resumed after pause.
    }

    override fun hide() {
        // This method is called when another screen replaces this one.
    }

    override fun dispose() {
        // Destroy screen's assets here.
    }

    val speed = 0.1f
    private fun handleInput(delta: Float) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            test.setPosition(test.x, test.y + speed * delta)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            test.setPosition(test.x - speed * delta, test.y)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            test.setPosition(test.x, test.y - speed * delta)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            test.setPosition(test.x + speed * delta, test.y)
        }
    }
}