package com.example.proyectointegrador.ui.layout

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.example.core.model.theme.OrangeMain
import com.example.proyectointegrador.R
enum class Destination(
    val route: String,
    val icon: Int,
    val label: String,
    val contentDescription: String
) {
    HOME("home", R.drawable.home, "Inicio", "Ir a inicio"),
    PROFILE("profile", R.drawable.user, "Perfil", "Ir a Perfil"),
}

@Composable
fun DefaultNavBar(
    selectedDestination: Int,
    onDestinationSelected: (Int) -> Unit,
    navController: NavController
) {
    NavigationBar(
        containerColor = Color.Transparent,
        contentColor = OrangeMain,
    ) {
        Destination.entries.forEachIndexed { index, destination ->
            NavigationBarItem(
                selected = selectedDestination == index,
                onClick = {
                    navController.navigate(destination.route)
                    onDestinationSelected(index)
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(destination.icon),
                        contentDescription = destination.contentDescription
                    )
                },
                label = { Text(destination.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor =Color.Black,
                    indicatorColor= OrangeMain,
                     selectedTextColor = OrangeMain
                )
            )
        }
    }
}