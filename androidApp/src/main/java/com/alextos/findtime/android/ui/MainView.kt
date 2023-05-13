package com.alextos.findtime.android.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.alextos.findtime.TimeZoneHelperImpl

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
        if (showAddDialog.value) {
            AddTimeZoneDialog(
                onDismiss = {
                    showAddDialog.value = false
                },
                onAdd = { timezone ->
                    showAddDialog.value = false
                    if (!currentTimezoneStrings.contains(timezone)) {
                        currentTimezoneStrings.add(timezone)
                    }
                }
            )
        }

        when (selectedIndex.value) {
            0 -> TimeZonesScreen(currentTimezoneStrings, it)
            1 -> FindMeetingScreen(timezoneStrings = currentTimezoneStrings)
        }
    }
}

@Composable
private fun AddTimeZoneDialog(onDismiss: () -> Unit, onAdd: (String) -> Unit) {
    val timezones = TimeZoneHelperImpl().getTimeZoneStrings()
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            LazyColumn(
                modifier = Modifier
                    .height(300.dp)
                    .width(300.dp)
            ) {
                items(timezones) { timezone ->
                    TimeZoneItem(timezone = timezone, onClick = onAdd)
                }
            }
        }
    }
}

@Composable
private fun TimeZoneItem(timezone: String, onClick: (String) -> Unit) {
    Text(
        text = timezone,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(timezone) }
    )
}
