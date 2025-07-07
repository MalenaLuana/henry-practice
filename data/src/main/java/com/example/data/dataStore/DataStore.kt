package com.example.data.dataStore

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.core.model.UserDto
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(
    @ApplicationContext  private val context:Context
) {
    private val USER_KEY = stringPreferencesKey("user_json")

    val user: Flow<UserDto?> = context.userDataStore.data.map { prefs ->
        prefs[USER_KEY]?.let {
            try {
                Json.decodeFromString<UserDto>(it)
            } catch (error: Exception) {
                Log.e("Login error",error.message?:"")
                null
            }
        }
    }

    suspend fun saveUser(user: UserDto) {
        context.userDataStore.edit { prefs ->
            prefs[USER_KEY] = Json.encodeToString(user)
        }
    }

    suspend fun clearUser() {
        context.userDataStore.edit { prefs ->
            prefs.remove(USER_KEY)
        }
    }
}
