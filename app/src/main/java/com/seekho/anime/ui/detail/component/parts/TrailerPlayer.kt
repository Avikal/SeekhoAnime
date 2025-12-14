package com.seekho.anime.ui.detail.component.parts

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.seekho.anime.ui.theme.AppBarDarkOpacity

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun TrailerPlayer(trailerUrl: String?) {
    // ✅ Fallback when trailer is not available
    if (trailerUrl.isNullOrEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                .background(
                    color = AppBarDarkOpacity,
                    shape = RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Trailer not available",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        return
    }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        factory = { context ->
            android.webkit.WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.mediaPlaybackRequiresUserGesture = false

                // ✅ Convert watch URL → embed URL
                val embedUrl = trailerUrl.replace(
                    "watch?v=",
                    "embed/"
                )

                loadUrl(embedUrl)
            }
        }
    )
}