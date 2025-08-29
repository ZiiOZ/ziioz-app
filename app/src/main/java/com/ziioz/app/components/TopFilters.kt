package com.ziioz.app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

enum class FeedFilter { Trending, Fresh, Underground }

@Composable
fun TopFilterChips(
    selected: FeedFilter,
    onChange: (FeedFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(
            selected = selected == FeedFilter.Trending,
            onClick = { onChange(FeedFilter.Trending) },
            label = { Text("Trending") }
        )
        FilterChip(
            selected = selected == FeedFilter.Fresh,
            onClick = { onChange(FeedFilter.Fresh) },
            label = { Text("Fresh") }
        )
        FilterChip(
            selected = selected == FeedFilter.Underground,
            onClick = { onChange(FeedFilter.Underground) },
            label = { Text("Underground") }
        )
    }
}
