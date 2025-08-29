package com.ziioz.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ziioz.app.model.Post
import com.ziioz.app.repository.FeedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class FeedUiState(
    val posts: List<Post> = emptyList(),
    val cursor: String? = null,
    val loading: Boolean = false,
    val error: String? = null
)

class FeedViewModel(
    private val repo: FeedRepository = FeedRepository()
) : ViewModel(), FeedViewModelContract {

    private val _state = MutableStateFlow(FeedUiState(loading = false)) // start NOT loading
    override val state: StateFlow<FeedUiState> = _state

    init {
        loadMore(null) // this will set loading=true and fetch
    }

    override fun loadMore(cursor: String?) {
        if (_state.value.loading) return
        _state.value = _state.value.copy(loading = true)

        viewModelScope.launch {
            runCatching { repo.feed(cursor) }
                .onSuccess { resp ->
                    _state.value = _state.value.copy(
                        posts = _state.value.posts + resp.items,
                        cursor = resp.nextCursor,
                        loading = false,
                        error = null
                    )
                }
                .onFailure { e ->
                    _state.value = _state.value.copy(
                        loading = false,
                        error = e.message ?: "Unknown error"
                    )
                }
        }
    }

    override fun like(id: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                posts = _state.value.posts.map {
                    if (it.id == id) it.copy(likes = it.likes + 1) else it
                }
            )
            runCatching { repo.like(id) }
        }
    }
}
