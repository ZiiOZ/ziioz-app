package com.ziioz.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(val route: String, val label: String, val icon: ImageVector) {
    object Hub : NavItem("home", "HUB", Icons.Filled.Home)
    object Z : NavItem("explore", "Z", Icons.Filled.Search)
    object Menu : NavItem("settings", "Menu", Icons.Filled.Menu)
}
