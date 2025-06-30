package com.example.feature.home.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.model.Product

@Composable
fun ProductItem(
    product: Product,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            RemoteImage(product.imageUrl)
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}