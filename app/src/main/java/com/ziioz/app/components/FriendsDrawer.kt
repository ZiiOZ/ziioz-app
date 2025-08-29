package com.ziioz.app.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private data class Friend(val name: String, val requestNote: String? = null)

@Composable
fun FriendsDrawer(
    open: Boolean,
    onClose: () -> Unit,
) {
    // Single gradient behind everything → no white seams
    val bgLeft   = Color(0xFF222830)
    val bgRight  = Color(0xFF1A2026)
    val headerBg = Color(0xFF2A3139)
    val white    = Color.White
    val dot      = Color(0xFF8A5CFF)

    // Demo data (keep Emma & Robert with notes)
    val friends = listOf(
        Friend("Emma",   "Wants to connect"),
        Friend("Oceanco"),
        Friend("Ashley"),
        Friend("Robert", "Request sent"),
        Friend("Ava"),
        Friend("Mia"),
        Friend("Liam"),
        Friend("Noah"),
        Friend("Amelia"),
        Friend("Olivia"),
        Friend("Ethan"),
        Friend("Zoe"),
    )

    // Top notifications in Friends column (3 lines space reserved)
    val topNotifications = listOf(
        "You have 2 new messages.",
        "Ashley mentioned you in a post.",
        "Security tip: enable 2-factor auth."
    )

    // Scrim
    AnimatedVisibility(visible = open, enter = fadeIn(), exit = fadeOut()) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.28f))
                .clickable(onClick = onClose)
        )
    }

    // Panel
    AnimatedVisibility(
        visible = open,
        enter = slideInHorizontally(tween(200)) { -it },
        exit  = slideOutHorizontally(tween(200)) { -it }
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 3.dp,
                shadowElevation = 12.dp,
                shape = MaterialTheme.shapes.extraLarge,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.90f)
                    .padding(12.dp)
            ) {
                Column(Modifier.fillMaxSize()) {

                    // Header (white text)
                    Surface(
                        color = headerBg,
                        contentColor = white,
                        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Friends",
                                color = white,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                            )
                            Spacer(Modifier.weight(1f))
                            Text(
                                "Close",
                                color = white,
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.clickable(onClick = onClose)
                            )
                        }
                    }

                    // Content (single gradient background)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Brush.horizontalGradient(listOf(bgLeft, bgRight)))
                            .padding(12.dp)
                    ) {
                        Row(Modifier.fillMaxSize()) {

                            // LEFT: Friends
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color.Transparent)
                                    .verticalScroll(rememberScrollState())
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                            ) {
                                Text(
                                    "Friends",
                                    color = white,
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
                                )
                                Spacer(Modifier.height(10.dp))

                                // 3-line notification area
                                Surface(
                                    color = Color.White.copy(alpha = 0.06f),
                                    contentColor = white,
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .heightIn(min = 60.dp) // ~3 lines
                                ) {
                                    Column(
                                        Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                                        verticalArrangement = Arrangement.spacedBy(2.dp)
                                    ) {
                                        topNotifications.take(3).forEach {
                                            Text(
                                                it,
                                                color = white,
                                                style = MaterialTheme.typography.labelSmall,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }
                                    }
                                }

                                Spacer(Modifier.height(14.dp))

                                friends.forEach { f ->
                                    FriendRow(
                                        name = f.name,
                                        requestNote = f.requestNote,
                                        hexSize = 82.dp, // 25% bigger (was ~66)
                                        dotColor = if (f.requestNote != null) dot else null,
                                        onClick = { /* TODO open profile */ }
                                    )
                                    Spacer(Modifier.height(18.dp))
                                }
                            }

                            // RIGHT: You
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color.Transparent)
                                    .verticalScroll(rememberScrollState())
                                    .padding(horizontal = 12.dp, vertical = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    "You",
                                    color = white,
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
                                )
                                Spacer(Modifier.height(16.dp))

                                HexAvatar(
                                    label = "Y",
                                    hexSize = 125.dp // 25% larger (was ~100)
                                )
                                Spacer(Modifier.height(12.dp))

                                Text("You", color = white, style = MaterialTheme.typography.titleSmall)
                                Text("1217 followers", color = white, style = MaterialTheme.typography.labelMedium)
                                Spacer(Modifier.height(12.dp))
                                Text("View", color = white, style = MaterialTheme.typography.labelLarge)
                                Spacer(Modifier.height(6.dp))
                                Text("Edit", color = white, style = MaterialTheme.typography.labelLarge)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FriendRow(
    name: String,
    requestNote: String?,
    hexSize: Dp,
    dotColor: Color?,
    onClick: () -> Unit
) {
    val white = Color.White

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            HexAvatar(label = name.first().uppercase(), hexSize = hexSize)
            if (dotColor != null) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .align(Alignment.TopEnd)
                        .offset(x = 4.dp, y = (-4).dp)
                        .clip(CircleShape)
                        .background(dotColor)
                )
            }
        }
        Spacer(Modifier.width(16.dp))
        Column {
            Text(name, color = white, style = MaterialTheme.typography.labelLarge)
            if (requestNote != null) {
                Text(
                    requestNote,
                    color = white,
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Clip
                )
            }
        }
    }
}
