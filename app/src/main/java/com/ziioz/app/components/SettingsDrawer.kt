package com.ziioz.app.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SettingsDrawer(
    open: Boolean,
    onClose: () -> Unit
) {
    // Scrim
    AnimatedVisibility(
        visible = open,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.22f))
                .clickable(onClick = onClose)
        )
    }

    // Panel
    AnimatedVisibility(
        visible = open,
        enter = slideInHorizontally(animationSpec = tween(220)) { it },   // from right
        exit = slideOutHorizontally(animationSpec = tween(220)) { it }
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd) {
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(320.dp),
                shape = MaterialTheme.shapes.extraLarge,
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 3.dp,
                shadowElevation = 8.dp
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("Settings", style = MaterialTheme.typography.titleLarge)
                    Spacer(Modifier.height(12.dp))

                    DrawerCard("Legal & Privacy") { /* TODO */ }
                    DrawerCard("Help & Support") { /* TODO */ }
                    DrawerCard("Developer: Commercial Interactive") { /* TODO */ }

                    Spacer(Modifier.height(12.dp))
                    TextButton(onClick = onClose) { Text("Close") }
                }
            }
        }
    }
}

/**
 * Shared card used by drawers.
 */
@Composable
fun DrawerCard(
    title: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    trailing: (@Composable () -> Unit)? = null,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable(enabled = enabled, onClick = onClick),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
        tonalElevation = 0.5.dp
    ) {
        Row(
            Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.weight(1f))
            trailing?.invoke()
        }
    }
}
