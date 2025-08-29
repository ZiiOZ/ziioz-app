// app/src/main/java/com/ziioz/app/viewmodel/DummyFeedViewModel.kt
package com.ziioz.app.viewmodel

import androidx.lifecycle.ViewModel
import com.ziioz.app.model.DummyFeed
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DummyFeedViewModel : ViewModel(), FeedViewModelContract {
    private val _state = MutableStateFlow(
        FeedUiState(posts = DummyFeed.posts, cursor = null, loading = false, error = null)
    )
    override val state: StateFlow<FeedUiState> = _state
    override fun like(id: String) {
        _state.value = _state.value.copy(
            posts = _state.value.posts.map { if (it.id == id) it.copy(likes = it.likes + 1) else it }
        )
    }
    override fun loadMore(cursor: String?) { /* no-op for dummy */ }
}
