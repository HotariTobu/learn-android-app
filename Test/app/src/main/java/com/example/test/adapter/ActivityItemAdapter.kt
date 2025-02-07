package com.example.test.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.model.ActivityItemModel

class ActivityItemAdapter(private val items: List<ActivityItemModel>) :
    RecyclerView.Adapter<ActivityItemAdapter.ActivityItemViewHolder>() {
    class ActivityItemViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {
        val button: Button = view.findViewById(R.id.activity_item_view_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_view, parent, false)
        return ActivityItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityItemViewHolder, position: Int) {
        val activityItem = items[position]

        holder.button.text = activityItem.name
        holder.button.setOnClickListener {
            val context = it.context
            val intent = Intent(context, activityItem.activity)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}