package com.example.escapefromtheblackhole.game

import android.graphics.Canvas

interface IObject {
    val layerLevel: Int
    fun init(surfaceSize: Size)
    fun dispose()
    fun update(delta: Int)
    fun draw(canvas: Canvas)
}