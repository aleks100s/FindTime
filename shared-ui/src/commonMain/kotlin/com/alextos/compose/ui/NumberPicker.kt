package com.alextos.compose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.runtime.Composable

@Composable
fun NumberPicker(hour: Int, range: IntRange, onValueChange: (Int) -> Unit) {
    Column {
        IconButton(
            onClick = { onValueChange(hour + 1) },
            enabled = hour < range.last
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowCircleUp,
                contentDescription = "Increase"
            )
        }

        Text("Hour: $hour")

        IconButton(
            onClick = {
                onValueChange(hour - 1)
            },
            enabled = hour > range.first
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowCircleDown,
                contentDescription = "Decrease"
            )
        }
    }
}