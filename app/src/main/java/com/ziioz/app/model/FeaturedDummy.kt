package com.ziioz.app.model

object FeaturedDummy {
    val posts = listOf(
        Post(
            id = "1",
            author = "Alice",
            avatarUrl = null,
            mediaType = MediaType.IMAGE,
            mediaUrl = "https://picsum.photos/600/900?random=1",
            caption = "Hello from Alice",
            likes = 12,
            boosted = false,
            pinned = false,
            shouted = false,
            createdAt = "2025-08-28T12:00:00Z"
        ),
        Post(
            id = "2",
            author = "Bob",
            avatarUrl = null,
            mediaType = MediaType.IMAGE,
            mediaUrl = "https://picsum.photos/600/900?random=2",
            caption = "Bob’s adventure",
            likes = 45,
            boosted = false,
            pinned = false,
            shouted = false,
            createdAt = "2025-08-28T12:01:00Z"
        ),
        Post(
            id = "3",
            author = "Clara",
            avatarUrl = null,
            mediaType = MediaType.IMAGE,
            mediaUrl = "https://picsum.photos/600/900?random=3",
            caption = "Clara vibes",
            likes = 88,
            boosted = true,
            pinned = false,
            shouted = false,
            createdAt = "2025-08-28T12:02:00Z"
        )
    )
}
