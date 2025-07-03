package com.example.feature.cart.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature.cart.CartViewModel
import com.example.feature.cart.ui.Strings

@Composable
fun CartBottomBar(
    onCheckoutClick: () -> Unit
) {
    val viewModel: CartViewModel = hiltViewModel()
    val cartItems by viewModel.cartItems.collectAsState()

    val total = cartItems.sumOf { it.price * it.quantity }

    Surface(
        tonalElevation = 3.dp,
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(Strings.TotalLabel, style = MaterialTheme.typography.labelMedium)
                Text(
                    text = "$${"%.2f".format(total)}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Button(onClick = onCheckoutClick) {
                Text(Strings.MakeOrder)
            }
        }
    }
}
