package com.ziioz.app.model

object DummyFeed {
    val posts = listOf(
        Post(
            id = "p1",
            author = "Nova",
            avatarUrl = null,
            mediaType = MediaType.IMAGE,
            mediaUrl = "https://picsum.photos/900/1600?random=11",
            caption = "City lights",
            likes = 23,
            createdAt = "2025-08-28T12:00:00Z"
        ),
        Post(
            id = "p2",
            author = "Orion",
            avatarUrl = null,
            mediaType = MediaType.IMAGE,
            mediaUrl = "https://picsum.photos/900/1600?random=12",
            caption = "Desert run",
            likes = 51,
            createdAt = "2025-08-28T12:05:00Z"
        ),
        Post(
            id = "p3",
            author = "Vega",
            avatarUrl = null,
            mediaType = MediaType.IMAGE,
            mediaUrl = "https://picsum.photos/900/1600?random=13",
            caption = "Harbor vibes",
            likes = 7,
            createdAt = "2025-08-28T12:10:00Z"
        ),
        Post(
            id = "p4",
            author = "Lyra",
            avatarUrl = null,
            mediaType = MediaType.IMAGE,
            mediaUrl = "https://picsum.photos/900/1600?random=14",
            caption = "Forest walk",
            likes = 88,
            createdAt = "2025-08-28T12:15:00Z"
        )
    )
}
