package com.example.proyectointegrador.ui

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

val LocalNavController = staticCompositionLocalOf <NavHostController> {
    error("NavController not provided")
}