package com.example.feature.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.core.model.CartItem
import com.example.core.model.Product
import com.example.core.model.theme.OrangeMain
import com.example.core.model.utils.toCartItem

@Composable
fun ProductItem(
    product: Product,
    onClick: (String) -> Unit = {},
    onAddToCartClick: (CartItem)->Unit = {}
) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable { onClick(product._id) }
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val y = size.height - strokeWidth / 2
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = strokeWidth
                    )
                },
            verticalAlignment =  Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            RemoteImage(src = product.imageUrl, alt = product.name)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(0.7f))
            {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.titleSmall
                )
            }
            IconButton(
                onClick = { onAddToCartClick(product.toCartItem()) },
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(OrangeMain)
                    .size(48.dp)

            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add to cart",
                    tint = Color.White

                )
            }

        }

}