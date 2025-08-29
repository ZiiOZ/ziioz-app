// app/src/main/java/com/ziioz/app/ui/navigation/BottomNavItem.kt
package com.ziioz.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search   // ✅ use Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String) {
    data object Home : BottomNavItem("HUB", Icons.Filled.Home, "home")
    data object Explore : BottomNavItem("Z", Icons.Filled.Search, "explore") // ✅
    data object Settings : BottomNavItem("Menu", Icons.Filled.Menu, "settings")
    companion object { val items = listOf(Home, Explore, Settings) }
}
