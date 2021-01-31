package com.example.composeapplication.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composeapplication.Utils.DataGenerator
import com.example.composeapplication.cars.Car
import java.text.NumberFormat
import java.util.*

class MainViewModel : ViewModel() {

    val cars: MutableState<List<Car>> = mutableStateOf(emptyList())

    fun addCar() {
        addToState(car = DataGenerator.randomCar())
    }

    fun removeCar(car: Car) {
        cars.value -= car
    }

    fun find(model: String) {
        cars.value = DataGenerator.find(model)
    }

    fun formatMoney(price: Double): String {
        return NumberFormat.getCurrencyInstance()
            .apply { currency = Currency.getInstance("USD") }
            .format(price)
    }

    private fun addToState(car: Car) {
        cars.value += car
    }
}