package com.example.core.model.utils

import com.example.core.model.CartItem
import com.example.core.model.Product

fun Product.toCartItem(): CartItem {
    return CartItem(
        id = this._id,
        name = this.name,
        price = this.price,
        imageUrl = this.imageUrl,
        quantity = 1
    )
}