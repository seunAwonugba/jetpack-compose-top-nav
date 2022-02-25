package com.example.composetopnav.repository

import com.example.composetopnav.model.MyCar

class MyCarRepository {
    fun getAllCars() : List<MyCar>{
        return listOf(
            MyCar(),
            MyCar(),
            MyCar(),
            MyCar()
        )
    }
}