package com.example.test

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test.databinding.ActivityTestDetectingSensorBinding

class TestDetectingSensorActivity : AppCompatActivity() {
    companion object {
        const val name = "TestDetectingSensorActivity"
    }

    private lateinit var binding: ActivityTestDetectingSensorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       binding = ActivityTestDetectingSensorBinding.inflate(layoutInflater)
       setContentView(binding.root)

        val builder = StringBuilder()
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL).sortedBy { it.type }
        var lastType = 0
        for (sensor in sensorList) {
            if (lastType!=sensor.type){
                builder.append(
                    "\n${
                        when (sensor.type) {
                            Sensor.TYPE_ACCELEROMETER -> "重力を含む加速度"
                            Sensor.TYPE_ACCELEROMETER_UNCALIBRATED -> "加速度見習い"
                            Sensor.TYPE_AMBIENT_TEMPERATURE -> "周囲温度"
                            Sensor.TYPE_DEVICE_PRIVATE_BASE -> "ベース"
                            Sensor.TYPE_GAME_ROTATION_VECTOR -> "ゲーム回転"
                            Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR -> "地磁気回転"
                            Sensor.TYPE_GRAVITY -> "重力"
                            Sensor.TYPE_GYROSCOPE -> "ジャイロ"
                            Sensor.TYPE_GYROSCOPE_UNCALIBRATED -> "ジャイロ見習い"
                            Sensor.TYPE_HEART_BEAT -> "鼓動"
                            Sensor.TYPE_HEART_RATE -> "心拍数"
                            Sensor.TYPE_HINGE_ANGLE -> "ヒンジ角度"
                            Sensor.TYPE_LIGHT -> "周囲光"
                            Sensor.TYPE_LINEAR_ACCELERATION -> "重力を含まない加速度"
                            Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT -> "低レイテンシ"
                            Sensor.TYPE_MAGNETIC_FIELD -> "磁場"
                            Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED -> "磁場見習い"
                            Sensor.TYPE_MOTION_DETECT -> "動き"
                            Sensor.TYPE_POSE_6DOF -> "ポーズの自由度"
                            Sensor.TYPE_PRESSURE -> "気圧"
                            Sensor.TYPE_PROXIMITY -> "近接"
                            Sensor.TYPE_RELATIVE_HUMIDITY -> "湿度"
                            Sensor.TYPE_ROTATION_VECTOR -> "回転"
                            Sensor.TYPE_SIGNIFICANT_MOTION -> "重要な動き"
                            Sensor.TYPE_STATIONARY_DETECT -> "動かない"
                            Sensor.TYPE_STEP_COUNTER -> "段差カウント"
                            Sensor.TYPE_STEP_DETECTOR -> "段差"
                            else -> "unknown ${sensor.type}"
                        }
                    }\n"
                )
                lastType=sensor.type
            }
            builder.append("\t${sensor.name}, ${sensor.vendor}\n")
        }
        binding.textView2.text = builder.toString()
    }
}