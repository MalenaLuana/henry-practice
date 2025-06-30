package com.example.feature.home.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun RemoteImage(src:String) {
    AsyncImage(
        model = src,
        contentDescription = "Imagen remota",
        modifier = Modifier.size(200.dp),
        contentScale = ContentScale.Crop
    )
}