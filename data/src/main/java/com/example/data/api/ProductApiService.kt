package com.example.data.api

import com.example.core.model.Product
import retrofit2.http.GET

interface ProductApiService {
    @GET("foods")
    suspend fun getProducts(): List<Product>
}
