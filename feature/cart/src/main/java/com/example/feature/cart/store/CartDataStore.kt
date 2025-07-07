package com.example.feature.cart.store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.core.model.CartItem
import com.example.data.dataStore.userDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Singleton


object CartPreferenceKeys {
    val CART_ITEMS = stringPreferencesKey("cart_items")
}

class CartDataStore (private val context: Context) {

    val cartItemsFlow:Flow<List<CartItem>> =context.userDataStore.data
        .map { prefs ->  prefs[CartPreferenceKeys.CART_ITEMS]?.let { json ->
        try {
            Json.decodeFromString<List<CartItem>>(json)
        } catch (e: Exception) {
            emptyList()
        }
    } ?: emptyList() }
    suspend fun saveCartItems(cartItems: List<CartItem>) {
        context.userDataStore.edit{ prefs ->
            prefs[CartPreferenceKeys.CART_ITEMS] = Json.encodeToString(cartItems)
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideCartDataStore(@ApplicationContext context: Context): CartDataStore {
        return CartDataStore(context)
    }
}