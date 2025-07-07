package com.example.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class LoginRequest (
    val email:String,
    val encryptedPassword:String
)

@Serializable
data class LoginResponse(
    val message: String,
    val user: UserDto
)

@Serializable
data class UserDto(
    val _id: String,
    val email: String,
    val fullName: String,
    val encryptedPassword: String,
    val createdAt: String,
    val updatedAt: String,
)
