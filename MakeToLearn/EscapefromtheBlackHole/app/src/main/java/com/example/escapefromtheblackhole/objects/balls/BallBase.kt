package com.example.escapefromtheblackhole.objects.balls

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.example.escapefromtheblackhole.DataContext
import com.example.escapefromtheblackhole.game.IObject
import com.example.escapefromtheblackhole.game.Size

open class BallBase(private val context: DataContext) : IObject {
    companion object {
        val radius = 30f
        private val moveFactor = 0.05f
        private val maxV = 300
    }

    override val layerLevel = 0

    var x = 0f; protected set
    var y = 0f; protected set

    protected var width = 0f; private set
    protected var height = 0f; private set
    protected var rect = RectF(); private set
    protected var vx = 0f
    protected var vy = 0f

    private val paint = Paint().apply { color = Color.YELLOW }

    override fun init(surfaceSize: Size) {
        width = surfaceSize.width.toFloat()
        height = surfaceSize.height.toFloat()
        rect = RectF(-radius, -radius, width + radius, height + radius)
        x = (width / 2).toFloat()
        y = (height / 2).toFloat()
    }

    override fun dispose() {}

    override fun update(delta: Int) {
        val t = delta * moveFactor

        var atx = sin(context.roll) * t
        x += (vx + atx / 2) * t
        if (Math.abs(vx) > maxV) {
            atx = 0f
        }
        vx += atx

        var aty = sin(context.pitch) * t
        y -= (vy + aty / 2) * t
        if (Math.abs(vy) > maxV) {
            aty = 0f
        }
        vy += aty
    }

    private inline fun sin(radian: Float): Float {
        return Math.sin(radian.toDouble()).toFloat()
    }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(x, y, radius, paint)
    }
}