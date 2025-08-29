package com.ziioz.app.ui.navigation

import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BottomBar(
    selectedRoute: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        NavItem("Home", Icons.Filled.Home, "home"),
        NavItem("Explore", Icons.Filled.Search, "explore"),
        NavItem("Boosts", Icons.Filled.Star, "boosts") // <-- Star instead of Bolt/FlashOn
    )

    NavigationBar(modifier = modifier) {
        items.forEach { item ->
            NavigationBarItem(
                selected = selectedRoute == item.route,
                onClick = { onItemSelected(item.route) },
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) }
            )
        }
    }
}
