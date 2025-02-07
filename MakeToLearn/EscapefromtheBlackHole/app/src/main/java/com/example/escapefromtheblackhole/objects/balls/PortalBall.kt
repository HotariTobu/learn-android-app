package com.example.escapefromtheblackhole.objects.balls

import com.example.escapefromtheblackhole.DataContext

class PortalBall(private val context: DataContext) : BallBase(context) {
    override fun update(delta: Int) {
        super.update(delta)

        if (x < rect.left) {
            x = rect.right
        } else if (x > rect.right) {
            x = rect.left
        }

        if (y < rect.top) {
            y = rect.bottom
        } else if (y > rect.bottom) {
            y = rect.top
        }
    }
}