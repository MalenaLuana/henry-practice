package com.example.core.model

data class Order(
    val orderId: String,
    val productIds: List<Product>,
    val total:Int,
    val timestamp: Int
)