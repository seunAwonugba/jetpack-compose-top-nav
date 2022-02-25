package com.example.composetopnav.model

data class MyCar(
    val carId : String? = null,
    val carName : String = "Volkswagen Transporter T4 2018",
    val carPrice : String = "₦2,300,000" ,
    val target : Int = 60,
    val completed : Int = 0,
    val condition : String = "Clean title, no accident history airbag not deployed"
)