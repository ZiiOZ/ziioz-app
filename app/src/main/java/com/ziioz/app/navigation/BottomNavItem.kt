// File: navigation/BottomNavItem.kt
package com.ziioz.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    object Home : BottomNavItem("Home", Icons.Filled.Home, "home")
    object Explore : BottomNavItem("Explore", Icons.Filled.Search, "explore")
    object Settings : BottomNavItem("Settings", Icons.Filled.Settings, "settings")
}
