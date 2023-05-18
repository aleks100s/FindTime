package com.alextos.compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberDialogState

@Composable
actual fun MeetingDialogWrapper(onDismiss: () -> Unit, content: @Composable () -> Unit) {
    Dialog(
        onCloseRequest = onDismiss,
        state = rememberDialogState(
            position = WindowPosition(Alignment.Center),
            size = DpSize(width = 300.dp, height = 300.dp)
        ),
        title = "Meeting",
        content = { content() }
    )
}