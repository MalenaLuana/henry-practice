package com.example.feature.profile.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.core.model.theme.OrangeMain

enum class InputType {
    TEXT,
    PASSWORD
}

@Composable
fun Input (
    value:String,
    onChange:(String)->Unit,
    label:String,
    type:InputType =InputType.TEXT
){
    val visualTransformation = when (type) {
        InputType.TEXT -> VisualTransformation.None
        InputType.PASSWORD -> PasswordVisualTransformation()
    }

    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        label = { Text(label) },
        visualTransformation = visualTransformation,
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = OrangeMain, focusedLabelColor = OrangeMain, cursorColor = OrangeMain)
    )
}