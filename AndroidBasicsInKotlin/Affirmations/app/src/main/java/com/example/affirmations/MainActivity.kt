package com.example.affirmations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.data.Datasource
import com.example.affirmations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myDataset =Datasource().loadAffirmations()
        binding.countOfAffirmationTextView.text=myDataset.size.toString()

        val recyclerView=binding.recyclerView
        recyclerView.adapter=ItemAdapter(this,myDataset)
        recyclerView.setHasFixedSize(true)
    }
}