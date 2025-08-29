package com.ziioz.app.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

data class HexChipStyle(val size: DpSize, val stroke: Dp) {
    companion object {
        fun small()  = HexChipStyle(DpSize(32.dp, 28.dp), 1.5.dp)
        fun medium() = HexChipStyle(DpSize(40.dp, 36.dp), 2.dp)
    }
}

@Composable
fun HexChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    style: HexChipStyle = HexChipStyle.small(),
    modifier: Modifier = Modifier
) {
    val border = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
    val bg = if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surface
    val strokePx = with(LocalDensity.current) { style.stroke.toPx() }

    Surface(color = bg, shape = MaterialTheme.shapes.small,
        modifier = modifier.size(style.size).clickable { onClick() }) {
        Box(contentAlignment = Alignment.Center) {
            Canvas(Modifier.fillMaxSize()) {
                val w = size.width
                val h = size.height
                val path = Path().apply {
                    moveTo(w * 0.25f, 0f)
                    lineTo(w * 0.75f, 0f)
                    lineTo(w, h * 0.5f)
                    lineTo(w * 0.75f, h)
                    lineTo(w * 0.25f, h)
                    lineTo(0f, h * 0.5f)
                    close()
                }
                drawPath(path, color = border, style = Stroke(width = strokePx))
            }
            Text(
                text = label,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun HexFriendsRow(
    friends: List<String>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    Row(
        modifier = modifier.padding(start = 12.dp, top = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        friends.forEachIndexed { idx, name ->
            HexChip(
                label = name.first().toString(),
                selected = idx == 0,
                onClick = { onClick(name) }
            )
        }
    }
}
