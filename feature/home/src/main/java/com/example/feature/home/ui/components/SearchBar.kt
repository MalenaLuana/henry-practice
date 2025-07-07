package com.example.feature.home.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.core.model.theme.OrangeLight

@ExperimentalMaterial3Api
@Composable
fun CustomSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = { },
        active = false,
        onActiveChange = {},
        placeholder = { Text("Buscar productos...") },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar")
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Limpiar")
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth(),
        colors = SearchBarDefaults.colors(
            containerColor = OrangeLight,
        )

    ) {}
}