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
import com.example.feature.profile.ui.LoginScreen
import com.example.proyectointegrador.ui.LocalNavController

@ExperimentalMaterial3Api
@Composable
fun LoginPage(){
    val navController = LocalNavController.current
    Scaffold(
        topBar = {
            TopAppBar(

                title = { Text("Volver") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }

    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            LoginScreen(
                onLoginSuccess = { navController.popBackStack() })
        }
    }
}