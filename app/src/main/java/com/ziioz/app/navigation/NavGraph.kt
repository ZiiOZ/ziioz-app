package com.ziioz.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ziioz.app.screens.FeedScreen
import com.ziioz.app.screens.ExploreScreen
import com.ziioz.app.screens.AppSettingsScreen

@Composable
fun AppNavGraph(nav: NavHostController) {
    NavHost(navController = nav, startDestination = NavItem.Feed.route) {
        composable(NavItem.Feed.route)     { FeedScreen() }          // <- you already have this
        composable(NavItem.Explore.route)  { ExploreScreen() }       // <- you already have this
        composable(NavItem.Settings.route) { AppSettingsScreen() }   // <- you already have this
    }
}
