package com.example.core.model

data class Product(
    val _id: String,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val hasDrink: Boolean,
    val description:String
)