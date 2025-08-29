package com.ziioz.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ziioz.app.components.HexChip
import com.ziioz.app.components.HexChipStyle

@Composable
fun FeedScreen(
    title: String = "ZiiOZ feed",
    onOpenDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        // Friend hex row
        val friends = remember {
            listOf("E", "O", "A", "R", "A", "M") // demo initials
        }
        var selected by remember { mutableStateOf("E") }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(friends) { ch ->
                HexChip(
                    label = ch,
                    selected = ch == selected,
                    onClick = { selected = ch },
                    style = HexChipStyle.small()
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "$title • Trending",
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "No posts yet",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
        )
    }
}
