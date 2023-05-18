package com.alextos.findtime.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alextos.compose.theme.AppTheme
import com.alextos.compose.ui.MainView
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Napier.base(DebugAntilog())
        setContent {
            com.alextos.compose.theme.AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    com.alextos.compose.ui.MainView {
                        TopAppBar(
                            title = {
                                when (it) {
                                    0 -> Text(getString(R.string.world_clocks))
                                    1 -> Text(getString(R.string.find_meeting))
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    com.alextos.compose.theme.AppTheme {
        com.alextos.compose.ui.MainView()
    }
}
