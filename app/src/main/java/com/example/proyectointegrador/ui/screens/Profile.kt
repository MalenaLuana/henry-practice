package com.example.proyectointegrador.ui.screens

import androidx.compose.foundation.layout.Box
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
import com.example.feature.profile.ui.ProfileScreen
import com.example.proyectointegrador.ui.LocalNavController
import com.example.proyectointegrador.ui.layout.DefaultNavBar

@ExperimentalMaterial3Api
@Composable
fun ProfilePage(
    selectedDestination:Int,
    onDestinationSelected:(Int)->Unit)
{
    val navController = LocalNavController.current
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Tu perfil") }
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
            ProfileScreen(onLoginClick = { navController.navigate("login") })
        }
    }
}