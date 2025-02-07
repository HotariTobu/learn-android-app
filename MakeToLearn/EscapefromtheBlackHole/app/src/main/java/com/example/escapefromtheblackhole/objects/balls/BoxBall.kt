package com.example.escapefromtheblackhole.objects.balls

import com.example.escapefromtheblackhole.DataContext

class BoxBall(private val context: DataContext) : BallBase(context) {
    override fun update(delta: Int) {
        super.update(delta)

        if (x < 0) {
            x = 0f
            vx = 0f
        } else if (x > width) {
            x = width
            vx = 0f
        }

        if (y < 0) {
            y = 0f
            vy = 0f
        } else if (y > height) {
            y = height
            vy = 0f
        }
    }
}