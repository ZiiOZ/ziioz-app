package com.ziioz.app.viewmodel

import kotlinx.coroutines.flow.StateFlow

interface FeedViewModelContract {
    val state: StateFlow<FeedUiState>
    fun like(id: String)
    fun loadMore(cursor: String?)
}
