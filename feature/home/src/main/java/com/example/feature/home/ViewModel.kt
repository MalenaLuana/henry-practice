package com.example.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.Product
import com.example.data.repository.DefaultProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(
private val repository: DefaultProductRepository
) : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()
   init {
       loadProducts()
   }
    private fun loadProducts() {
        viewModelScope.launch {
            repository.getProducts()
                .catch {error -> Log.e("Home viewModel error:","Error al cargar los productos",error)}
                .collect{
                 _products.value = it
                }
        }
    }

}