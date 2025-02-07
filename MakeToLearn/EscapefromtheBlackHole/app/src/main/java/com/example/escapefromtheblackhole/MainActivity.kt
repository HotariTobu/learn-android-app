package com.example.escapefromtheblackhole

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.escapefromtheblackhole.scenes.StayScene
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var stayScene: StayScene

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stayScene = StayScene(
            surfaceView.holder,
            Event(getSystemService(Context.SENSOR_SERVICE) as SensorManager)
        )
    }

    override fun onResume() {
        super.onResume()

        stayScene.resume(surfaceView.holder)
    }

    override fun onPause() {
        super.onPause()

        stayScene.pause()
    }

    override fun onDestroy() {
        super.onDestroy()

        stayScene.stop()
    }
}