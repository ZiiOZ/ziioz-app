package com.ziioz.app.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HexAvatar(
    label: String,
    hexSize: Dp,
    stroke: Dp = 2.dp,
    labelColor: Color = Color.White,
) {
    Box(
        modifier = Modifier.size(hexSize),
        contentAlignment = Alignment.Center
    ) {
        Canvas(Modifier.size(hexSize)) {
            val w = size.width
            val h = size.height
            val p = Path().apply {
                moveTo(w * 0.5f, 0f)
                lineTo(w,       h * 0.25f)
                lineTo(w,       h * 0.75f)
                lineTo(w * 0.5f, h)
                lineTo(0f,      h * 0.75f)
                lineTo(0f,      h * 0.25f)
                close()
            }
            drawPath(p, color = Color.White, style = Stroke(width = stroke.toPx()))
        }
        Text(
            text = label.take(1),
            color = labelColor,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
    }
}
