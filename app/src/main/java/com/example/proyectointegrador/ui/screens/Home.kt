package com.example.proyectointegrador.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
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
import androidx.navigation.NavController
import com.example.feature.cart.CartViewModel
import com.example.feature.home.ui.HomeScreen
import com.example.proyectointegrador.ui.layout.DefaultNavBar

@ExperimentalMaterial3Api
@Composable
fun HomePage (navController: NavController,cartViewModel: CartViewModel,selectedDestination:Int,onDestinationSelected:(Int)->Unit){
    val insets = WindowInsets.safeDrawing.only(WindowInsetsSides.Vertical)
    Scaffold (
        contentWindowInsets = insets,
        topBar = {
            TopAppBar(
                title = { Text("PedidosDespués") },
                actions = {
                    IconButton(onClick = {navController.navigate("cart"){launchSingleTop=true} }) {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Carrito")
                    }
                },
            )
        },
        bottomBar = {
            DefaultNavBar(
                navController = navController,
                selectedDestination = selectedDestination,
                onDestinationSelected = onDestinationSelected
            )
        }
    ) { innerPadding  ->
        Box(modifier = Modifier.padding(innerPadding )){
            HomeScreen (
                onProductClick = { id -> Log.d("MainScreen", "ProductClick: $id")},
                onAddToCartClick = { cartItem -> cartViewModel.addItemToCart(cartItem) }
            )
        }
    }
}