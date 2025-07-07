package com.example.feature.profile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.model.theme.OrangeMain
import com.example.feature.profile.language.Strings
import com.example.feature.profile.viewModels.LoginViewModel
import com.example.feature.profile.viewModels.ProfileViewModel

@Composable
fun ProfileScreen(
    onLoginClick: () -> Unit
) {
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val user by profileViewModel.user.collectAsState(initial = null)

    val authViewMode:LoginViewModel = hiltViewModel()
    if (user == null) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(Strings.emptyUserTitle, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(16.dp))
            Button (
                onClick = onLoginClick,
                colors = ButtonDefaults.buttonColors(containerColor = OrangeMain)
            ) {
                Text(Strings.loginBtn)
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Text("${Strings.greetingTitle}, ${user?.fullName}!")
            Button(onClick = { authViewMode.logOut() }){
                Text(Strings.logoutBtn)
            }
        }
    }
}
