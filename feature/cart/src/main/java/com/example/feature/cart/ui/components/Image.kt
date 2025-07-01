package com.example.feature.cart.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun RemoteImage(src:String,alt:String?) {
    AsyncImage(
        model = src,
        contentDescription = alt?:"Imagen remota",
        modifier = Modifier.size(64.dp).clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}