package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
            val toast = Toast.makeText(this, "Dice Rolled!!", Toast.LENGTH_SHORT)
            toast.show()

            rollDice()
        }
    }

    private fun rollDice() {
        val myDice = Dice(6)
        val number = myDice.roll()
        val image = when (number) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        val imageView: ImageView = findViewById(R.id.imageView)
        imageView.setImageResource(image)
        imageView.contentDescription = number.toString()
    }
}

/**
 *
 */
class Dice(private val sides: Int) {
    fun roll(): Int {
        return (1..sides).random()
    }
}