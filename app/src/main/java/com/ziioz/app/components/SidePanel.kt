package com.ziioz.app.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ziioz.app.navigation.MainNavItems

@Composable
fun DrawerContent(nav: NavController, onEntryClicked: () -> Unit) {
    ModalDrawerSheet {
        Text("ZiiOZ", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(16.dp))
        MainNavItems.forEach { item ->
            NavigationDrawerItem(
                label = { Text(item.label) },
                selected = nav.currentDestination?.route == item.route,
                onClick = {
                    nav.navigate(item.route) {
                        popUpTo(nav.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                    onEntryClicked()
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                modifier = Modifier.fillMaxWidth().padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}
