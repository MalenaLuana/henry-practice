package com.example.feature.profile.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.dataStore.UserPreferences
import com.example.data.repository.AuthRepository
import com.example.feature.profile.ui.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginUiState())
    val loginState: StateFlow<LoginUiState> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = _loginState.value.copy(isLoading = true, errorMessage = null)
            try {
                val response = authRepository.login(email, password)
                userPreferences.saveUser(response.user)
                _loginState.value = _loginState.value.copy(isLoading = false, isSuccess = true)
            } catch (e: Exception) {
                _loginState.value = _loginState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?:"Tuvimos un problema para iniciar sesión, por favor intenta de nuevo mas tarde."
                )
            }
        }
    }
     fun logOut(){
         viewModelScope.launch {
             try {
                 userPreferences.clearUser()
             }catch (error:Exception){
                 Log.e(
                     "Logout error",
                     error.message?:"Error al eliminar el usuario")
        }}

    }
}
