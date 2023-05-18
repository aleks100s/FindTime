package com.alextos.compose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.alextos.findtime.TimeZoneHelper
import com.alextos.findtime.TimeZoneHelperImpl

@Composable
fun FindMeetingScreen(timezoneStrings: List<String>) {
    val listState = rememberLazyListState()
    val startTime = remember { mutableStateOf(8) }
    val endTime = remember { mutableStateOf(17) }
    val selectedTimeZones = remember {
        val selected = SnapshotStateMap<Int, Boolean>()
        for (i in timezoneStrings.indices) {
            selected[i] = true
        }
        selected
    }
    val timezoneHelper: TimeZoneHelper = TimeZoneHelperImpl()
    val showMeetingDialog = remember { mutableStateOf(false) }
    val meetingHours = remember { SnapshotStateList<Int>() }

    if (showMeetingDialog.value) {
        MeetingDialog(
            meetingHours = meetingHours,
            onDismiss = { showMeetingDialog.value = false }
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.size(16.dp))

        Text(
           modifier = Modifier
               .fillMaxWidth()
               .wrapContentWidth(Alignment.CenterHorizontally),
            text = "Time Range",
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            Spacer(modifier = Modifier.size(16.dp))
            NumberTimeCard(label = "Start", hour = startTime)
            Spacer(modifier = Modifier.size(32.dp))
            NumberTimeCard(label = "End", hour = endTime)
        }

        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
                text = "Time Zones",
                style = MaterialTheme.typography.h6
            )
        }
        Spacer(modifier = Modifier.size(16.dp))

        LazyColumn(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            state = listState
        ) {
            itemsIndexed(timezoneStrings) { index, timezone ->
                Surface(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Checkbox(
                            checked = selectedTimeZones[index] == true,
                            onCheckedChange = {
                                selectedTimeZones[index] = it
                            }
                        )
                        Text(
                            text = timezone,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }

        Spacer(Modifier.weight(0.1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(horizontal = 4.dp)
        ) {
            OutlinedButton(
                onClick = {
                    meetingHours.clear()
                    meetingHours.addAll(
                        timezoneHelper.search(
                            startHour = startTime.value,
                            endHour = endTime.value,
                            timeZoneStrings = getSelectedTimeZones(
                                timezoneStrings = timezoneStrings,
                                selectedHours = selectedTimeZones
                            )
                        )
                    )
                    showMeetingDialog.value = true
                }
            ) {
                Text("Search")
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
    }
}

fun getSelectedTimeZones(
    timezoneStrings: List<String>,
    selectedHours: Map<Int, Boolean>
): List<String> {
    val selectedTimeZones = mutableListOf<String>()
    for (i in timezoneStrings.indices) {
        if (selectedHours[i] == true) {
            selectedTimeZones.add(timezoneStrings[i])
        }
    }
    return selectedTimeZones
}
