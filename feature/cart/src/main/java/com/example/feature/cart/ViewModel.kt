package com.example.feature.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.CartItem
import com.example.feature.cart.store.CartDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartDataStore: CartDataStore
):ViewModel(){
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()
    init {
        viewModelScope.launch {
            cartDataStore.cartItemsFlow.collect {
                _cartItems.value = it
            }
        }
    }
    private fun saveCart() {
        viewModelScope.launch {
            cartDataStore.saveCartItems(_cartItems.value)
        }
    }
    fun  addItemToCart(item:CartItem){
        Log.d("cart", "ProductClick: $item")
        val currentItems = _cartItems.value.toMutableList()
        val index = currentItems.indexOfFirst { it.id ==item.id }
        if (index >= 0) {
            val updated = currentItems[index].copy(quantity = currentItems[index].quantity + 1)
            currentItems[index] = updated
        } else {
            currentItems.add(item)
        }
        _cartItems.value = currentItems
        Log.d("cart", "Items actuales: $currentItems")
        saveCart()
    }

    fun removeItemFromCart(id:String){
        val updatedList = _cartItems.value.mapNotNull { item ->
            if (item.id == id) {
                if (item.quantity > 1) {
                    item.copy(quantity = item.quantity - 1)
                } else {
                    null
                }
            } else {
                item
            }
        }
        _cartItems.value = updatedList
        saveCart()
    }

    fun clearCart(){
        _cartItems.value= emptyList()
        saveCart()
    }
}
