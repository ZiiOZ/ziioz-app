// app/src/main/java/com/ziioz/app/screens/SettingsScreen.kt
package com.ziioz.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        Text("App Settings", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(24.dp))
        Text("• Legal & Privacy")
        Text("• Help & Support")
        Spacer(Modifier.height(12.dp))
        Text("Developer: COMMERCIAL INTERACTIVE", style = MaterialTheme.typography.bodySmall)
    }
}
