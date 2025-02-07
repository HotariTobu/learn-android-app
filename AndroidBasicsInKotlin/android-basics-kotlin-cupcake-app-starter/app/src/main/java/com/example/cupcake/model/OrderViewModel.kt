package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class OrderViewModel : ViewModel() {
    companion object {
        val PRICE_PER_CUPCAKE = 2.0
        val PRICE_FOR_SAME_DAY_PICKUP = 3.0
    }

    private val _orderQuantity = MutableLiveData<Int>()
    val orderQuantity: LiveData<Int> = _orderQuantity

    private val _cupcakeFlavor = MutableLiveData<String>()
    val cupcakeFlavor: LiveData<String> = _cupcakeFlavor

    private val _pickupDate = MutableLiveData<String>()
    val pickupDate: LiveData<String> = _pickupDate

    val dateOptions = getPickupOptions()

    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price){
        NumberFormat.getCurrencyInstance().format(it)
    }

    fun resetOrder() {
        setQuantity(0)
        setFlavor("")
        setDate(dateOptions[0])
    }

    fun setQuantity(numberCupcakes: Int) {
        _orderQuantity.value = numberCupcakes
        updatePrice()
    }

    fun setFlavor(desiredFlavor: String) {
        _cupcakeFlavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String) {
        _pickupDate.value = pickupDate
        updatePrice()
    }

    fun hasNoFlavorSet(): Boolean {
        return _cupcakeFlavor.value.isNullOrEmpty()
    }

    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calender = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calender.time))
            calender.add(java.util.Calendar.DATE, 1)
        }
        return options
    }

    private fun updatePrice() {
        var calculatedPrice = (orderQuantity.value ?: 0) * PRICE_PER_CUPCAKE
        if (dateOptions[0] == _pickupDate.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }
}