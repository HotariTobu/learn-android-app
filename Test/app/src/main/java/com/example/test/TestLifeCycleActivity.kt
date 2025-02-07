package com.example.test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.test.databinding.ActivityTestLifeCycleBinding

class TestLifeCycleActivity : AppCompatActivity() {
    companion object{
        const val name="TestLifeCycleActivity"
    }

    private lateinit var binding: ActivityTestLifeCycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       binding = ActivityTestLifeCycleBinding.inflate(layoutInflater)
       setContentView(binding.root)

        title= name

        binding.backToMainActivityButton.setOnClickListener {
            finish()
        }

        Log.d(name, "onCreate Called")
    }


    override fun onStart() {
        super.onStart()
        Log.d(name, "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(name, "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(name, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(name, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(name, "onDestroy Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(name, "onRestart Called")
    }
}