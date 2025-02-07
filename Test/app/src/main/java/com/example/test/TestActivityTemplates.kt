package com.example.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test.activityTemplates.ActivityTemplateItems
import com.example.test.adapter.ActivityItemAdapter
import com.example.test.databinding.ActivityTestTemplatesBinding

class TestActivityTemplates : AppCompatActivity() {
    companion object {
        const val name = "TestActivityTemplates"
    }

    private lateinit var binding: ActivityTestTemplatesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestTemplatesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityTemplateItemListRecyclerView.adapter =
            ActivityItemAdapter(ActivityTemplateItems.items)
        binding.activityTemplateItemListRecyclerView.setHasFixedSize(true)
    }
}