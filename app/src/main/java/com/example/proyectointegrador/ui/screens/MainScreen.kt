package com.example.proyectointegrador.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature.cart.CartViewModel
import com.example.feature.cart.ui.CartScreen
import com.example.feature.cart.ui.components.CartBottomBar

@ExperimentalMaterial3Api
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val cartViewModel:CartViewModel = hiltViewModel()
    val selectedDestinationState = rememberSaveable { mutableIntStateOf(0) }
    val selectedDestination = selectedDestinationState.intValue

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(16.dp)
        ){
            composable("home") {
               HomePage(
                   navController = navController,
                   cartViewModel=cartViewModel,
                   selectedDestination = selectedDestination,
                   onDestinationSelected ={selectedDestinationState.intValue=it}
               )
            }
            composable("cart") {
                Scaffold (
                    topBar = {
                        TopAppBar(

                            title = { Text("Tu carrito") },
                            navigationIcon = {
                                IconButton(onClick = { navController.navigate("home")}) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Localized description"
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = {navController.navigate("cart"){launchSingleTop=true} }) {
                                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Carrito")
                                }
                            },
                        )
                    },
                    bottomBar = { CartBottomBar(onCheckoutClick = {}) }

                ) { padding ->
                    Box(modifier = Modifier.padding(padding)){
                        CartScreen ()
                    }}
            }
            composable("profile"){
                ProfilePage(
                    navController=navController,
                    selectedDestination=selectedDestination,
                    onDestinationSelected ={selectedDestinationState.intValue=it}
                )
            }
        }

    }
