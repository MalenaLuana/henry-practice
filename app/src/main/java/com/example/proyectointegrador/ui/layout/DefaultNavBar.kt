package com.example.proyectointegrador.ui.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

enum class Destination(
    val route: String,
    val icon: ImageVector,
    val label: String,
    val contentDescription: String
) {
    HOME("home", Icons.Default.Home, "Inicio", "Ir a inicio"),
    PROFILE("profile", Icons.Default.Person, "Profile", "Ir a Perfil"),
}

@Composable
fun DefaultNavBar(
    selectedDestination: Int,
    onDestinationSelected: (Int) -> Unit,
    navController: NavController
) {
    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
        Destination.entries.forEachIndexed { index, destination ->
            NavigationBarItem(
                selected = selectedDestination == index,
                onClick = {
                    navController.navigate(destination.route)
                    onDestinationSelected(index)
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = destination.contentDescription
                    )
                },
                label = { Text(destination.label) }
            )
        }
    }
}