package com.example.feature.cart.ui.components

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.model.theme.OrangeMain
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
        color = Color.Transparent,
        modifier = Modifier.fillMaxWidth().padding(20.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(Strings.TotalLabel, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "$${"%.2f".format(total)}",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Button(onClick = onCheckoutClick, colors = ButtonDefaults.buttonColors(containerColor = OrangeMain)) {
                Text(Strings.MakeOrder)
            }
        }
    }
}
