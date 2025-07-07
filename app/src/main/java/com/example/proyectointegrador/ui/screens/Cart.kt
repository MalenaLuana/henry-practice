package com.example.proyectointegrador.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.feature.cart.ui.CartScreen
import com.example.feature.cart.ui.components.CartBottomBar
import com.example.proyectointegrador.ui.LocalNavController
import com.example.proyectointegrador.ui.language.Strings

@ExperimentalMaterial3Api
@Composable
fun CartPage (){
    val navController = LocalNavController.current
    Scaffold(
        topBar = {
            TopAppBar(

                title = { Text(Strings.cartTitle) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back button"
                        )
                    }
                }
            )
        },
        bottomBar = { CartBottomBar(onCheckoutClick = {})
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            CartScreen()
        }
    }
}