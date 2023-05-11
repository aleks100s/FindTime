package com.alextos.findtime.android.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.alextos.findtime.android.theme.AppTheme

sealed class Screen(val title: String) {
    object TimeZonesScreen : Screen("Timezones")
    object FindTimeScreen : Screen("Find Time")
}

data class BottomNavigationItem(
    val route: String,
    val icon: ImageVector,
    val iconContentDescription: String
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        Screen.TimeZonesScreen.title,
        Icons.Filled.Language,
        "Timezones"
    ),
    BottomNavigationItem(
        Screen.FindTimeScreen.title,
        Icons.Filled.Place,
        "Find Time"
    )
)

@Composable
fun MainView(actionBarFun: topBarFun = { emptyComposable() }) {
    val showAddDialog = remember { mutableStateOf(false) }
    val currentTimezoneStrings = remember { SnapshotStateList<String>() }
    val selectedIndex = remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            actionBarFun(selectedIndex.value)
        },
        floatingActionButton = {
            if (selectedIndex.value == 0) {
                FloatingActionButton(
                    modifier = Modifier.padding(16.dp),
                    onClick = {
                        showAddDialog.value = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add"
                    )
                }
            }
        },
        bottomBar = {
            BottomNavigation {
                bottomNavigationItems.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = {
                            Icon(item.icon, contentDescription = item.iconContentDescription)
                        },
                        selected = selectedIndex.value == index,
                        onClick = {
                            selectedIndex.value = index
                        }
                    )
                }
            }
        }
    ) {

    }
}