package com.example.data.repository

import com.example.core.model.LoginRequest
import com.example.core.model.LoginResponse
import com.example.data.api.AuthApiService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApi: AuthApiService
) {
    suspend fun login(email: String, password: String): LoginResponse {
        return authApi.login(LoginRequest(email, password))
    }
}