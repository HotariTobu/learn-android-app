package com.example.getorientation

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity(R.layout.activity_main), SensorEventListener {
    private val matrixSize = 16
    private var acValues = FloatArray(3)
    private var mgValues = FloatArray(3)
    private val orValues = FloatArray(3)
    private lateinit var timerTask: TimerTask

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) {
            return
        }

        val inR = FloatArray(matrixSize)
        val outR = FloatArray(matrixSize)
        val i = FloatArray(matrixSize)

        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> acValues = event.values.clone()
            Sensor.TYPE_MAGNETIC_FIELD -> mgValues = event.values.clone()
        }

        SensorManager.getRotationMatrix(inR, i, acValues, mgValues)
        SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_X, SensorManager.AXIS_Z, outR)
        SensorManager.getOrientation(outR, orValues)
    }

    private inline fun radianToDegree(radian: Float): Float {
        return radian * 180 / Math.PI.toFloat()
    }

    override fun onResume() {
        super.onResume()

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, magneticField, SensorManager.SENSOR_DELAY_NORMAL)

        timerTask = Timer().schedule(0, 1000) {
            Handler(Looper.getMainLooper()).post {
                txt1.text = "方位角: ${radianToDegree(orValues[0])}\n" +
                        "傾斜角: ${radianToDegree(orValues[1])}\n" +
                        "回転角: ${radianToDegree(orValues[2])}"
            }
        }
    }

    override fun onPause() {
        super.onPause()

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(this)

        timerTask.cancel()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}