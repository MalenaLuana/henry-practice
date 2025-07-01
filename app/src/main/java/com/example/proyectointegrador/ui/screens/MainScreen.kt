package com.example.proyectointegrador.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature.cart.CartViewModel
import com.example.feature.cart.ui.CartScreen
import com.example.feature.home.ui.HomeScreen

@ExperimentalMaterial3Api
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val cartViewModel:CartViewModel = hiltViewModel()
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Pedidos Después") },
                actions = {
                    IconButton(onClick = {navController.navigate("cart"){launchSingleTop=true} }) {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Carrito")
                    }
                }
            )
        }
    ) { padding ->
        NavHost(navController = navController, startDestination = "home", modifier = Modifier.padding(padding)){
            composable("home") {
                HomeScreen (
                    onProductClick = { id -> Log.d("MainScreen", "ProductClick: $id")},
                    onAddToCartClick = { cartItem -> cartViewModel.addItemToCart(cartItem) }
                )
            }
            composable("cart") {
                CartScreen()
            }
        }

    }
}
