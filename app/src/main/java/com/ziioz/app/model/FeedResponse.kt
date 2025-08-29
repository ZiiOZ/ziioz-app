package com.ziioz.app.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedResponse(
    val items: List<Post>,
    val nextCursor: String?
)
