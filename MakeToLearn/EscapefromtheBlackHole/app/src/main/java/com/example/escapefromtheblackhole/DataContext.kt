package com.example.escapefromtheblackhole

import com.example.escapefromtheblackhole.objects.balls.BallBase
import com.example.escapefromtheblackhole.objects.blackholes.BlackholeBase

class DataContext(
    private val event: Event
) {
    val azimuth get() = event.azimuth
    val pitch get() = event.pitch
    val roll get() = event.roll

    var isGone = false

    lateinit var ball: BallBase
    val blackholeList= mutableListOf<BlackholeBase>()
}