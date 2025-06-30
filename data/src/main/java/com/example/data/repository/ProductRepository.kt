package com.example.data.repository
import com.example.core.model.Product
import com.example.data.api.ProductApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultProductRepository @Inject constructor(
    private val apiService: ProductApiService
) {
    fun getProducts(): Flow<List<Product>> = flow {
        val products = apiService.getProducts()
        emit(products)
    }
}