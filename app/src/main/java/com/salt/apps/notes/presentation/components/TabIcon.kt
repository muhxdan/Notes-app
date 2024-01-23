package com.salt.apps.notes.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.salt.apps.notes.R

@Composable
fun TabIcon(
    isSelectedTab: Boolean,
    selectedTab: Int,
    clickIcon: () -> Unit,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    val resource = when {
        isDarkTheme && isSelectedTab -> {
            if (selectedTab == 0) R.drawable.ic_dark_filled_note
            else R.drawable.ic_dark_filled_todo
        }

        isDarkTheme -> R.drawable.ic_outlined_note
        isSelectedTab -> {
            if (selectedTab == 0) R.drawable.ic_light_filled_note
            else R.drawable.ic_light_filled_todo
        }

        else -> R.drawable.ic_outlined_note
    }

    Image(
        painter = painterResource(id = resource),
        contentDescription = "Tab Icon",
        modifier = Modifier
            .clip(RoundedCornerShape(3.dp))
            .clickable { clickIcon() }
            .size(22.dp),
    )
}