package com.ziioz.app.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import kotlin.math.abs

@Composable
fun DrawerEdgeHotspot(
    modifier: Modifier = Modifier,
    onOpen: () -> Unit
) {
    val thresholdPx = with(LocalDensity.current) { 24.dp.toPx() }
    var dragTotal by remember { mutableStateOf(0f) }

    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(24.dp)
            // Open on a short swipe from the edge (either direction)
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        if (abs(dragTotal) >= thresholdPx) onOpen()
                        dragTotal = 0f
                    }
                ) { _, dragAmount ->
                    dragTotal += dragAmount
                }
            }
            // Open on tap (no ripple)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onOpen() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Dot()
            Dot()
        }
    }
}

@Composable
private fun Dot() {
    Box(
        Modifier
            .size(6.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f))
    )
}
