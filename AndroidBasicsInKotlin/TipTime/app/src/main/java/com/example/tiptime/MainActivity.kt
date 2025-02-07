package com.example.tiptime

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )
        }

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

    private fun calculateTip() {
        val stringCost = binding.costOfServiceEditText.text.toString()
        val cost = stringCost.toDoubleOrNull()
        if (cost == null) {
            binding.resultView.text = ""
            return
        }

        val tipPercentage = when (binding.tipOption.checkedRadioButtonId) {
            R.id.option_15percent -> 0.15
            R.id.option_18percent -> 0.18
            else -> 0.20
        }

        var tip = cost * tipPercentage

        val isRoundUp = binding.roundUpSwitch.isChecked
        if (isRoundUp) {
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.resultView.text = getString(R.string.tip_amount, formattedTip)
    }
}