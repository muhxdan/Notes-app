package com.salt.apps.notes.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.salt.apps.notes.presentation.theme.md_theme_dark_hint
import com.salt.apps.notes.presentation.theme.md_theme_light_hint

@Composable
fun TextField(
    title: String,
    placeHolder: String,
    onValueChange: (String) -> Unit,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    val placeHolderColor = when {
        isDarkTheme -> md_theme_dark_hint
        else -> md_theme_light_hint
    }

    val placeHolderStyle = when (placeHolder) {
        "Title" -> MaterialTheme.typography.titleLarge.copy(color = placeHolderColor)
        else -> MaterialTheme.typography.bodyLarge.copy(color = placeHolderColor)
    }

    val textFieldColors = TextFieldDefaults.colors(
        focusedIndicatorColor = MaterialTheme.colorScheme.background,
        unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
        disabledIndicatorColor = MaterialTheme.colorScheme.background,
        unfocusedContainerColor = MaterialTheme.colorScheme.background,
        focusedContainerColor = MaterialTheme.colorScheme.background,
    )

    val textStyle = when (placeHolder) {
        "Title" -> MaterialTheme.typography.titleLarge
        else -> MaterialTheme.typography.bodyLarge
    }

    TextField(
        value = title,
        placeholder = {
            Text(
                text = placeHolder,
                style = placeHolderStyle
            )
        },
        onValueChange = onValueChange,
        colors = textFieldColors,
        textStyle = textStyle,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(horizontal = 3.dp)
    )
}