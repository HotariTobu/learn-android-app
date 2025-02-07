package com.example.escapefromtheblackhole.scenes

import android.view.SurfaceHolder
import com.example.escapefromtheblackhole.DataContext
import com.example.escapefromtheblackhole.Event
import com.example.escapefromtheblackhole.game.Manager
import com.example.escapefromtheblackhole.objects.balls.BoxBall

class StayScene(holder: SurfaceHolder, event: Event) : Manager(holder) {

    private val dataContext = DataContext(event)

    init {
        dataContext.ball = BoxBall(dataContext)
        objectCollection.add(dataContext.ball)

        eventList.add(event)
    }
}