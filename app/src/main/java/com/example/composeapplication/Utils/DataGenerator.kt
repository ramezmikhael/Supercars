package com.example.composeapplication.Utils

import com.example.composeapplication.cars.Car
import com.example.composeapplication.R
import java.util.*
import kotlin.random.Random

class DataGenerator {

    companion object {
        fun randomCar(): Car {
            return cars.get(Random.nextInt(0, cars.size))
        }

        fun find(maker: String) = cars.filter { it.maker.toLowerCase().contains(maker.toLowerCase()) }

        private val cars = listOf(
            Car("458 Italia", "Ferrari", 500, R.drawable.ferrari_458_italia, 318, 245_000.0),
            Car("Panamera", "Porsche", 450, R.drawable.porsche_panamera, 300, 91_950.0),
            Car("SLS", "Mercedes", 571, R.drawable.mercedes_sls, 317, 260_200.0),
            Car("Avantador", "Lamborghini", 759, R.drawable.lamborghini_avantador, 350, 417_826.0),
            Car("Viper", "Dodge", 645, R.drawable.dodge_viper, 257, 107_995.0)
        )
    }
}