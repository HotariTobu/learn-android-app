package com.example.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test.adapter.ActivityItemAdapter
import com.example.test.data.ActivityItems
import com.example.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityItemListRecyclerView.adapter=ActivityItemAdapter(ActivityItems.items)
        binding.activityItemListRecyclerView.setHasFixedSize(true)
    }
}