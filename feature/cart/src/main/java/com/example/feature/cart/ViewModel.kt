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
        // Escuchar los datos guardados al iniciar
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
        saveCart()
    }

    fun removeItemFromCart(id:Int){
        _cartItems.value = _cartItems.value.filterNot { it.id == id }
        saveCart()
    }

    fun clearCart(){
        _cartItems.value= emptyList()
        saveCart()
    }
}
