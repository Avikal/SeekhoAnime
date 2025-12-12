package com.seekho.anime.ui.detail.component.parts

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun TrailerPlayer(youtubeUrl: String?) {
    if (youtubeUrl == null) return

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                loadUrl(youtubeUrl.replace("watch?v=", "embed/"))
            }
        }
    )
}