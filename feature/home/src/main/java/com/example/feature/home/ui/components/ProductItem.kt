package com.example.feature.home.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.model.CartItem
import com.example.core.model.Product
import com.example.core.model.utils.toCartItem
import com.example.feature.home.ui.Strings

@Composable
fun ProductItem(
    product: Product,
    onClick: (Int) -> Unit = {},
    onAddToCartClick: (CartItem)->Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(product.id) },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)){
            RemoteImage(src=product.imageUrl, alt = product.name)
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.bodyMedium
            )
                Button (onClick = { onAddToCartClick(product.toCartItem()) }) {
                    Text(Strings.AddToCart)
                }
        }
    }
}
}