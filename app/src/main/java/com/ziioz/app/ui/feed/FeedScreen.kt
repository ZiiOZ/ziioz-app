package com.ziioz.app.ui.feed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ziioz.app.viewmodel.FeedViewModelContract

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeedScreen(vm: FeedViewModelContract) {
    val state by vm.state.collectAsState()
    val listState = rememberLazyListState()
    val fling = rememberSnapFlingBehavior(listState)

    if (state.posts.isEmpty() && state.loading) {
        Text(
            "Loading feed…",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(24.dp)
        )
        return
    }

    LazyRow(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        flingBehavior = fling,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(
            items = state.posts,
            key = { _, post -> post.id }
        ) { index, post ->
            // Uses your separate FeedCard composable (ui/feed/FeedCard.kt)
            FeedCard(
                post = post,
                onLike = { vm.like(post.id) }
            )

            // Preload when near the end
            if (index >= state.posts.lastIndex - 2 && state.cursor != null && !state.loading) {
                LaunchedEffect(state.cursor) { vm.loadMore(state.cursor) }
            }
        }
    }
}
