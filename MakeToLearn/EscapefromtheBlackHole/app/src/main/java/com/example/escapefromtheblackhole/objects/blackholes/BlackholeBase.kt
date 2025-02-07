package com.example.escapefromtheblackhole.objects.blackholes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.escapefromtheblackhole.DataContext
import com.example.escapefromtheblackhole.game.IObject
import com.example.escapefromtheblackhole.game.Size

class BlackholeBase(private val context: DataContext) : IObject {
    companion object {
        val radiusRange = 30..100
        val moveFactor = 1
    }

    override val layerLevel = 0

    var x = 0f; private set
    var y = 0f; private set

    private val paint = Paint().apply { color = Color.BLACK }
    private var width = 0
    private var height = 0
    private var firstRadius = radiusRange.random().toFloat()
    private var radius = firstRadius

    override fun init(surfaceSize: Size) {
        width = surfaceSize.width
        height = surfaceSize.height
        x = (0..width).random().toFloat()
        y = (0..height).random().toFloat()
    }

    override fun dispose() {}

    override fun update(delta: Int) {
        // TODO: 2021/02/19 scaling 

        val difX = x - context.ball.x
        val difY = y - context.ball.y
        if (difX * difX + difY * difY < radius * radius) {
            context.isGone = true
        }
    }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(x, y, radius, paint)
    }
}