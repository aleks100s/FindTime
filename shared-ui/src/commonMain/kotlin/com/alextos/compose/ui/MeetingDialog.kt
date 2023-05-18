package com.alextos.compose.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
expect fun MeetingDialogWrapper(onDismiss: () -> Unit, content: @Composable () -> Unit)

@Composable
fun MeetingDialog(meetingHours: List<Int>, onDismiss: () -> Unit) {
    MeetingDialogWrapper(onDismiss = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .height(300.dp)
                    .width(300.dp)
            ) {
                items(meetingHours) { hour ->
                    Text(text = hour.toString())
                }
            }
        }
    }
}