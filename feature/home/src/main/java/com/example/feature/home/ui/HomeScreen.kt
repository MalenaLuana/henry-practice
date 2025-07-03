package com.example.feature.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.collectAsState
import com.example.feature.home.HomeViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.feature.home.ui.components.ProductItem
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import com.example.core.model.CartItem

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onProductClick: (String) -> Unit,
    onAddToCartClick: (CartItem) -> Unit
)
{
    val viewModel: HomeViewModel = hiltViewModel()
    val products by viewModel.products.collectAsState()

        Column (
            modifier = modifier.fillMaxSize().padding(16.dp)
        ) {
            Text(Strings.HomeSubtitle)
            LazyColumn (
                modifier = Modifier.weight(1f)
            ){
                items(products) { product ->
                    ProductItem( product, onProductClick,onAddToCartClick)
                }
            }

        }

}


