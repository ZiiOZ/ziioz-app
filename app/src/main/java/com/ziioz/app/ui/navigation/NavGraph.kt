package com.ziioz.app.ui.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ziioz.app.model.FeaturedDummy
import com.ziioz.app.ui.cube.CubeScreen
import com.ziioz.app.ui.feed.FeedScreen
import com.ziioz.app.viewmodel.FeedViewModel

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route,
        modifier = modifier
    ) {
        composable(BottomNavItem.Home.route) {
            CubeScreen(
                featured = FeaturedDummy.posts, // Dummy data until API wired
                onExploreFeed = { navController.navigate(BottomNavItem.Explore.route) }
            )
        }
        composable(BottomNavItem.Explore.route) {
            FeedScreen(vm = com.ziioz.app.viewmodel.DummyFeedViewModel())
        }
        composable(BottomNavItem.Settings.route) {
            Text("Settings coming soon", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
