@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.ziioz.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ziioz.app.components.BottomBar
import com.ziioz.app.components.DrawerContent
import com.ziioz.app.navigation.AppNavGraph
import kotlinx.coroutines.launch   // <-- needed for scope.launch { ... }

@Composable
fun ZiiOZApp() {
    val nav = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(nav) {
                scope.launch { drawerState.close() }   // <-- now valid
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("ZiiOZ") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Outlined.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            bottomBar = { BottomBar(nav) }
        ) { padding ->
            Surface(Modifier.padding(padding)) {
                AppNavGraph(nav)
            }
        }
    }
}
