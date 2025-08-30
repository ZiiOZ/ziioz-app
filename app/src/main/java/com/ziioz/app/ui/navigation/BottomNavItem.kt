package com.ziioz.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "HUB", Icons.Filled.Home)
    object Explore : BottomNavItem("explore", "Z", Icons.Filled.Search)
    object Settings : BottomNavItem("settings", "Menu", Icons.Filled.Menu)
}
