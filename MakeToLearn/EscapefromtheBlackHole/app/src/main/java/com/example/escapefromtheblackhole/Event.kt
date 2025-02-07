package com.example.escapefromtheblackhole

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.example.escapefromtheblackhole.game.IEvent

class Event(private val sensorManager: SensorManager) : IEvent, SensorEventListener {
    companion object {
        private val period = SensorManager.SENSOR_DELAY_NORMAL
        private val matrixSize = 16
    }

    private val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

    var azimuth = 0f; private set
    var pitch = 0f; private set
    var roll = 0f; private set

    private var acValues = FloatArray(3)
    private var mgValues = FloatArray(3)

    override fun resume() {
        sensorManager.registerListener(this, accelerometer, period)
        sensorManager.registerListener(this, magneticField, period)
    }

    override fun pause() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) {
            return
        }

        val inR = FloatArray(matrixSize)
        val outR = FloatArray(matrixSize)
        val i = FloatArray(matrixSize)
        val orValues = FloatArray(3)

        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> acValues = event.values.clone()
            Sensor.TYPE_MAGNETIC_FIELD -> mgValues = event.values.clone()
        }

        SensorManager.getRotationMatrix(inR, i, acValues, mgValues)
        SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_X, SensorManager.AXIS_Y, outR)
        SensorManager.getOrientation(outR, orValues)

        azimuth = orValues[0]
        pitch = orValues[1]
        roll = orValues[2]
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}