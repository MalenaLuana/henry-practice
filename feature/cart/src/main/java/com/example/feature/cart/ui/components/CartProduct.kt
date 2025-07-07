package com.example.feature.cart.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core.model.CartItem
import com.example.core.model.theme.GreenMain
import com.example.core.model.R

@Composable
fun CartProductItem(
    item: CartItem,
    onRemoveClick: (CartItem) -> Unit,
    onAddClick:(CartItem)->Unit
) {
        val removeIcon = if(item.quantity>1){R.drawable.remove}else{R.drawable.trash}
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            RemoteImage(src = item.imageUrl, alt = item.name)
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "$${item.price}",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = GreenMain
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = { onRemoveClick(item) }
                ) {
                    Icon(
                        modifier =  Modifier.size(20.dp),
                        imageVector =ImageVector.vectorResource(removeIcon),
                        contentDescription = "Remove item"
                    )
                }

                Text(
                    text = "${item.quantity}",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.titleMedium
                )

                IconButton(
                    onClick = { onAddClick(item) }
                ) {
                    Icon(
                        modifier =  Modifier.size(20.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.add),
                        contentDescription = "Add item"
                    )
                }
            }
        }

}