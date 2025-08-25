package com.ziioz.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ziioz.app.network.ApiService
import com.ziioz.app.network.RetrofitInstance
import com.ziioz.app.network.UiPost
import kotlinx.coroutines.launch

@Composable
fun FeedScreen(api: ApiService = RetrofitInstance.api) {
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    var posts by remember { mutableStateOf<List<UiPost>>(emptyList()) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("ZiiOZ feed", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        Button(
            enabled = !loading,
            onClick = {
                loading = true; error = null
                scope.launch {
                    runCatching { api.feed() }
                        .onSuccess { posts = it.posts }
                        .onFailure { error = it.message }
                    loading = false
                }
            }
        ) { Text(if (loading) "Loading…" else "Load /feed") }

        Spacer(Modifier.height(16.dp))
        when {
            error != null -> Text("Error: $error", color = MaterialTheme.colorScheme.error)
            posts.isEmpty() && !loading -> Text("No posts yet")
            else -> LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(posts) { p ->
                    ElevatedCard(Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
                        Column(Modifier.padding(12.dp)) {
                            Text(p.author, style = MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.height(4.dp))
                            Text(p.content, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}
