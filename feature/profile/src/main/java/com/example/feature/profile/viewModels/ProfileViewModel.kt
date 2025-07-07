package com.example.feature.profile.viewModels

import androidx.lifecycle.ViewModel
import com.example.data.dataStore.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel() {

    val user = userPreferences.user
}