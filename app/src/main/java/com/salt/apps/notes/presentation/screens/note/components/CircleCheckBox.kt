package com.salt.apps.notes.presentation.screens.note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.salt.apps.notes.R
import com.salt.apps.notes.presentation.theme.md_theme_light_onPrimary

@Composable
fun CircleCheckbox(selected: Boolean) {
    val tint = if (selected) md_theme_light_onPrimary else MaterialTheme.colorScheme.surfaceVariant
    val background =
        if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background

    Box(
        modifier = Modifier
            .background(background, shape = CircleShape)
            .size(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_check),
            tint = tint,
            contentDescription = "checkbox"
        )
    }
}