package com.ziioz.app.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DynamicFeed
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Settings

sealed class NavItem(val route: String, val label: String, val icon: ImageVector) {
    data object Feed     : NavItem("feed",     "Feed",     Icons.Outlined.DynamicFeed)
    data object Explore  : NavItem("explore",  "Explore",  Icons.Outlined.Explore)
    data object Settings : NavItem("settings", "Settings", Icons.Outlined.Settings)
}

val MainNavItems = listOf(NavItem.Feed, NavItem.Explore, NavItem.Settings)
