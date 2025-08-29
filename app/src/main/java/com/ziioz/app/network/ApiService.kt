package com.ziioz.app.network

import com.ziioz.app.model.FeedResponse
import com.ziioz.app.model.Post
import retrofit2.http.*

interface ApiService {
    @GET("/api/featured")
    suspend fun getFeatured(): List<Post>

    @GET("/api/feed")
    suspend fun getFeed(@Query("cursor") cursor: String? = null): FeedResponse

    @POST("/api/posts/{id}/like")  suspend fun like(@Path("id") id: String)
    @POST("/api/posts/{id}/boost") suspend fun boost(@Path("id") id: String)
    @POST("/api/posts/{id}/pin")   suspend fun pin(@Path("id") id: String)
    @POST("/api/posts/{id}/shout") suspend fun shout(@Path("id") id: String)
}
