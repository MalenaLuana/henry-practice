package com.example.data.api

import com.example.core.model.LoginRequest
import com.example.core.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("users/login")
    suspend fun login(@Body request:LoginRequest):LoginResponse
}