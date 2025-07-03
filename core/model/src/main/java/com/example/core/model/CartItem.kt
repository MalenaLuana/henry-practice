package com.example.core.model

import kotlinx.serialization.Serializable

@Serializable
data class CartItem(
    val id: String,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val quantity: Int = 1
 )