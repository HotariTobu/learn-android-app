package com.example.escapefromtheblackhole.objects.balls

import com.example.escapefromtheblackhole.DataContext

class BoundBall(private val context: DataContext) : BallBase(context) {
    companion object {
        private val boundK = 0.5f
    }

    override fun update(delta: Int) {
        super.update(delta)

        if (x < 0) {
            x = 0f
            vx = -vx * boundK
        } else if (x > width) {
            x = width
            vx = -vx * boundK
        }

        if (y < 0) {
            y = 0f
            vy = -vy * boundK
        } else if (y > height) {
            y = height
            vy = -vy * boundK
        }
    }
}