package com.example.feature.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.example.core.model.CartItem
import com.example.feature.home.language.Strings
import com.example.feature.home.ui.components.CustomSearchBar

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
    var searBarQuery by remember { mutableStateOf("") }

    val filteredProducts = products.filter {
        it.name.contains(searBarQuery, ignoreCase = true) ||
                it.description.contains(searBarQuery, ignoreCase = true)
    }

        Column(
            modifier = modifier.fillMaxSize()
        ) {
            CustomSearchBar(
                query = searBarQuery,
                onQueryChange = {searBarQuery = it}
            )
            Column (modifier = Modifier.padding(16.dp)) {

                Text(Strings.ProductsTitle,style = MaterialTheme.typography.headlineLarge)
            }
            LazyColumn (
                modifier = Modifier.weight(1f)
            ){
                items(filteredProducts) { product ->
                    ProductItem( product, onProductClick,onAddToCartClick)
                }
            }

        }

}


