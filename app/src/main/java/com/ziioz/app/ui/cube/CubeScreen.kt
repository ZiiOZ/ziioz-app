// app/src/main/java/com/ziioz/app/ui/cube/CubeScreen.kt
package com.ziioz.app.ui.cube

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ziioz.app.model.Post
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun CubeScreen(
    featured: List<Post>,
    onExploreFeed: () -> Unit
) {
    var index by remember { mutableStateOf(0) }
    val angle = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    fun rotate(dir: Int) {
        val target = angle.value + (90f * dir)
        scope.launch {
            angle.animateTo(
                targetValue = target,
                animationSpec = tween(durationMillis = 280, easing = FastOutSlowInEasing)
            )
            // cycle index 0..5
            index = (index + if (dir > 0) 1 else 5) % 6
        }
    }

    Box(Modifier.fillMaxSize()) {
        if (featured.isEmpty()) {
            // Safe placeholder if you haven’t wired the API yet
            Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text(
                    "ZiiCube showcase\n(no featured posts yet)",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            // Simulate a cube face with perspective + rotation
            AsyncImage(
                model = featured.getOrNull(index)?.mediaUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        // perspective
                        cameraDistance = 24 * density
                        rotationY = angle.value % 360f
                        // subtle depth scale
                        val d = abs((rotationY % 180f) - 90f) / 90f
                        val s = 0.92f + 0.08f * (1f - d)
                        scaleX = s
                        scaleY = s
                    }
            )
        }

        // Controls
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(onClick = { rotate(-1) }) { Text("◀") }
            Button(onClick = onExploreFeed) { Text("Explore Feed") }
            OutlinedButton(onClick = { rotate(+1) }) { Text("▶") }
        }
    }
}
