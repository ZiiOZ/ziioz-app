package com.ziioz.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ziioz.app.ui.navigation.BottomNavigationBar
import com.ziioz.app.ui.navigation.NavGraph

@Composable
fun App() {
    val nav = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(nav) }
    ) { inner -> NavGraph(nav, Modifier.padding(inner)) }
}
