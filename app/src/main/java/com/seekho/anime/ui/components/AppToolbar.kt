package com.seekho.anime.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.seekho.anime.ui.theme.AppBarLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    title: String,
    showBack: Boolean = false,
    onBack: (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            if (showBack && onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            actions?.invoke()
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppBarLight,      // 🎯 Background color
            titleContentColor = Color.White, // Optional
            navigationIconContentColor = Color.White
        )
    )
}