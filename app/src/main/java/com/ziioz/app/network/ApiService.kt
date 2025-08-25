package com.ziioz.app.network

import retrofit2.http.GET

data class FeedResp(val posts: List<UiPost>)
data class UiPost(val id: Int, val author: String, val content: String)

interface ApiService {
    @GET("health") suspend fun health(): Map<String, String>
    @GET("feed")   suspend fun feed(): FeedResp
}
