package com.ziioz.app.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Post(
    val id: String,
    val author: String,
    val avatarUrl: String? = null,
    val mediaType: MediaType, // IMAGE | VIDEO
    val mediaUrl: String,
    val caption: String? = null,
    val likes: Int = 0,
    val boosted: Boolean = false,
    val pinned: Boolean = false,
    val shouted: Boolean = false,
    val createdAt: String
)

enum class MediaType { IMAGE, VIDEO }
