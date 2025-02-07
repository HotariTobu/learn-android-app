package com.example.escapefromtheblackhole.game

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.SurfaceHolder
import java.util.*
import kotlin.concurrent.schedule

open class Manager(protected val holder: SurfaceHolder) : SurfaceHolder.Callback {
    companion object {
        val fps = 30
        val period = 1000L / fps
        val backgroundColor = Color.WHITE
    }

    var isRunning = false; private set
    var time = 0L; private set

    protected val objectCollection = ObjectCollection()
    protected val eventList = mutableListOf<IEvent>()

    private lateinit var timerTask: TimerTask
    private val handler = Handler(Looper.getMainLooper())

    init {
        holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {}

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        objectCollection.init(Size(width, height))
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {}

    fun resume(holder: SurfaceHolder) {
        if (isRunning) {
            return
        }

        var lastTime = System.currentTimeMillis()
        time = lastTime
        timerTask = Timer().schedule(0, period) {
            time = System.currentTimeMillis()
            objectCollection.update((time - lastTime).toInt())
            lastTime = time

            handler.post {
                val canvas = holder.lockCanvas()
                if (canvas != null) {
                    canvas.drawColor(backgroundColor)
                    objectCollection.draw(canvas)
                    holder.unlockCanvasAndPost(canvas)
                }
            }
        }

        for (event in eventList) {
            event.resume()
        }

        isRunning = true
    }

    fun pause() {
        timerTask.cancel()

        for (event in eventList) {
            event.pause()
        }

        isRunning = false
    }

    fun stop() {
        pause()
        objectCollection.dispose()
    }
}