package com.example.feature.cart.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature.cart.CartViewModel
import com.example.feature.cart.ui.components.CartProductItem
import androidx.compose.runtime.getValue

@Composable
fun CartScreen( ){
    val viewModel: CartViewModel = hiltViewModel()
    val cartItems by viewModel.cartItems.collectAsState()

    if (cartItems.isEmpty()) {
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("El carrito está vacío")
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(cartItems) { item ->
                CartProductItem(item = item, onRemoveClick = {viewModel.removeItemFromCart(item.id)})
            }
        }
    }
}