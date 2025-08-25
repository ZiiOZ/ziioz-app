package com.ziioz.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UploadScreen(onUpload: () -> Unit = {}) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Upload", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        Button(onClick = onUpload, colors = ButtonDefaults.buttonColors()) {
            Text("Pick image & upload")
        }
    }
}
