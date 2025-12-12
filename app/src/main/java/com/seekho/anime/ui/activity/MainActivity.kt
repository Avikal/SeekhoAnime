package com.seekho.anime.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import com.seekho.anime.ui.ExitConfirmationDialog
import com.seekho.anime.ui.home.component.HomeNavHost
import com.seekho.anime.ui.theme.SeekhoAnimeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SeekhoAnimeTheme {
                var showExitDialog by remember { mutableStateOf(false) }
                SideEffect {
                    // Force override dynamic colors
                    WindowCompat.getInsetsController(window, window.decorView)
                        .apply {
                            isAppearanceLightStatusBars = false
                        }
                }

                BackHandler {
                    showExitDialog = true
                }
                HomeNavHost()

                // Exit Dialog
                if (showExitDialog) {
                    ExitConfirmationDialog(
                        onExit = { finish() },
                        onDismiss = { showExitDialog = false }
                    )
                }
            }
        }
    }
}