package com.example.feature.profile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.model.theme.OrangeMain
import com.example.feature.profile.language.Strings
import com.example.feature.profile.ui.components.Input
import com.example.feature.profile.viewModels.LoginViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    val viewModel:LoginViewModel = hiltViewModel()
    val loginState by viewModel.loginState.collectAsState()
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable{ mutableStateOf("") }

    LaunchedEffect (loginState.isSuccess) {
        if (loginState.isSuccess) {
            onLoginSuccess()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
    ) {
        Text(Strings.loginTitle, style = MaterialTheme.typography.headlineLarge)
        Text(Strings.loginSubtitle, style = MaterialTheme.typography.bodyMedium)

        Spacer(Modifier.height(24.dp))

        Input(
            value = email,
            onChange = { email = it },
            label = "Email",
        )
        Spacer(Modifier.height(16.dp))
        Input(
            value = password,
            onChange = { password = it },
            label =  "Contraseña",
        )

        Spacer(Modifier.height(24.dp))


        Button(
            onClick = { viewModel.login(email, password) },
            enabled = !loginState.isLoading,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = OrangeMain)
        ) {
            if (loginState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    color = Color.White
                )
            } else {
                Text("Ingresar")
            }
        }

        loginState.errorMessage?.let { error ->
            Spacer(Modifier.height(16.dp))
            Text(error, color = MaterialTheme.colorScheme.error)
        }
    }
}