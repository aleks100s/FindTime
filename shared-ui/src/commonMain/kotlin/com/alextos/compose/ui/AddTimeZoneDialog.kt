package com.alextos.compose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.alextos.findtime.TimeZoneHelperImpl

@Composable
expect fun AddTimeDialogWrapper(onDismiss: () -> Unit, content: @Composable () -> Unit)

@Composable
fun AddTimeZoneDialog(onDismiss: () -> Unit, onAdd: (String) -> Unit) {
    val timezones = TimeZoneHelperImpl().getTimeZoneStrings()

    AddTimeDialogWrapper(onDismiss = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp)
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