package com.salt.apps.notes.presentation.screens.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.salt.apps.notes.presentation.screens.main.MainViewModel

@Composable
fun CustomSearchBar(mainViewModel: MainViewModel, isDarkTheme: Boolean = isSystemInDarkTheme()) {
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    val placeHolderColor = when {
        isDarkTheme -> Color(0xff686868)
        else -> Color(0xFF8F8F8F)
    }

    val textFieldColors = TextFieldDefaults.colors(
        focusedIndicatorColor = MaterialTheme.colorScheme.background,
        unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
        disabledIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(50.dp)
            .padding(horizontal = 8.dp)
    ) {
        TextField(
            value = text,
            placeholder = {
                Text(
                    text = "Search Notes",
                    style = MaterialTheme.typography.bodyLarge.copy(color = placeHolderColor)
                )
            },
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            onValueChange = { text = it },
            colors = textFieldColors,
            textStyle = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .weight(1f)
                .onFocusChanged {
                    if (it.isFocused) {
                        mainViewModel.toggleSearch()
                    }
                },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.padding(8.dp),
                )
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        if (mainViewModel.screenState.value.isSearchActive) {
            Text(
                text = "Cancel",
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clickable(
                        onClick = {
                            mainViewModel.clearSearchActive()
                            focusManager.clearFocus()
                        },
                    ),
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary)
            )
        }
    }
}