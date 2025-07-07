package com.example.proyectointegrador.ui.screens


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.proyectointegrador.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.feature.cart.CartViewModel
import com.example.feature.home.ui.HomeScreen
import com.example.proyectointegrador.ui.LocalNavController
import com.example.proyectointegrador.ui.language.Strings
import com.example.proyectointegrador.ui.layout.DefaultNavBar
import androidx.compose.ui.unit.LayoutDirection
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun HomePage (cartViewModel: CartViewModel,selectedDestination:Int,onDestinationSelected:(Int)->Unit){
    val navController = LocalNavController.current
    val insets = WindowInsets.safeDrawing.only(WindowInsetsSides.Vertical)
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        contentWindowInsets = insets,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(Strings.appName) },
                actions = {
                    IconButton(
                        modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.Black)
                        .size(48.dp),onClick = {navController.navigate("cart"){launchSingleTop=true} }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.cart),
                            contentDescription = "Carrito",
                            tint = Color.White
                        )
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
    ) { padding  ->
        Box( modifier = Modifier.padding(
            top = 70.dp,
            start = padding.calculateStartPadding(LayoutDirection.Ltr),
            end = padding.calculateEndPadding(LayoutDirection.Ltr),
            bottom = padding.calculateBottomPadding()
        )){
            HomeScreen (
                onProductClick = { id -> Log.d("MainScreen", "ProductClick: $id")},
                onAddToCartClick = { cartItem ->
                    cartViewModel.addItemToCart(cartItem)
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = Strings.addToCart,
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            )
        }
    }
}