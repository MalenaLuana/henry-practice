package com.example.feature.cart.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature.cart.CartViewModel
import com.example.feature.cart.ui.components.CartProductItem
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CartScreen( ){
    val viewModel: CartViewModel = hiltViewModel()
    val cartItems by viewModel.cartItems.collectAsState()

    if (cartItems.isEmpty()) {
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(Strings.EmptyCart)
        }
    } else {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier
                .fillMaxSize(),


            ) {
                items(cartItems, key = {it.id}) { item ->
                    CartProductItem(
                        item = item,
                        onRemoveClick = {viewModel.removeItemFromCart(item.id)},
                        onAddClick = {viewModel.addItemToCart(item)}
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
            }
        }
        }
    }
}