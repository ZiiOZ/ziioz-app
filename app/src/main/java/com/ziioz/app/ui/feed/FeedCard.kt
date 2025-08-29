package com.ziioz.app.ui.feed

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ziioz.app.model.Post
import com.ziioz.app.model.MediaType

@Composable
fun FeedCard(
    post: Post,
    onLike: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Box(Modifier.weight(1f)) {
            when (post.mediaType) {
                MediaType.IMAGE -> AsyncImage(
                    model = post.mediaUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                MediaType.VIDEO -> Text("Video Placeholder") // replace with ExoPlayer later
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(onClick = onLike) { Text("♥ ${post.likes}") }
            OutlinedButton(onClick = { }) { Text("Boost") }
            OutlinedButton(onClick = { }) { Text("Pin") }
            OutlinedButton(onClick = { }) { Text("Shout") }
            OutlinedButton(onClick = { }) { Text("💬") }
        }
    }
}
