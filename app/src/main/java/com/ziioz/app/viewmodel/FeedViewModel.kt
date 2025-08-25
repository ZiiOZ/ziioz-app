package com.ziioz.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ziioz.app.network.ApiService
import com.ziioz.app.network.RetrofitInstance
import com.ziioz.app.network.UiPost
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class FeedUiState(
    val loading: Boolean = false,
    val posts: List<UiPost> = emptyList(),
    val error: String? = null
)

class FeedViewModel(
    private val api: ApiService = RetrofitInstance.api
) : ViewModel() {

    private val _state = MutableStateFlow(FeedUiState())
    val state: StateFlow<FeedUiState> = _state

    fun load() {
        if (_state.value.loading) return
        _state.value = _state.value.copy(loading = true, error = null)
        viewModelScope.launch {
            runCatching { api.feed() }                 // suspend fun returns FeedResp
                .onSuccess { resp -> _state.value = FeedUiState(posts = resp.posts) }
                .onFailure { e -> _state.value = FeedUiState(error = e.message) }
        }
    }
}
