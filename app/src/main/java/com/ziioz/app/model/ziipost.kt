package com.ziioz.app.model

import com.google.gson.annotations.SerializedName

data class ZiiPost(
    val id: String,
    val author: String,
    val content: String,
    @SerializedName("image_url") val imageUrl: String? = null
)
