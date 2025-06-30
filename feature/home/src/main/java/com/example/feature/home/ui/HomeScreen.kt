package com.example.feature.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.runtime.collectAsState
import com.example.feature.home.HomeViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.feature.home.ui.components.ProductItem
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text

@Composable
fun HomeScreen()
{
    val viewModel: HomeViewModel = hiltViewModel()
    val products by viewModel.products.collectAsState()

    Column (modifier = Modifier.fillMaxSize()) {
        Text("Productos")
        LazyColumn (
            modifier = Modifier.weight(1f)
        ){
            items(products) { product ->
                ProductItem(product = product, onClick = {})
                }
            }
    }
}


