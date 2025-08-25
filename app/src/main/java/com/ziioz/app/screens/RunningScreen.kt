package com.ziioz.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RunningScreen(
    onOpenFeed: () -> Unit   // <--- add this
) {
    // ...your existing UI (title, API_BASE_URL, Ping button, status text)...

    Spacer(Modifier.height(12.dp))
    Button(onClick = onOpenFeed) {
        Text("Open Feed")    // <--- new button to switch to FeedScreen()
    }
}
