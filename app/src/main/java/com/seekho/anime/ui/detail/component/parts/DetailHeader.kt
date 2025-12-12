package com.seekho.anime.ui.detail.component.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.seekho.anime.domain.model.Anime

@Composable
fun DetailHeader(anime: Anime?) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {

        AsyncImage(
            model = anime?.imageUrl,
            contentDescription = anime?.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Gradient overlay for readability
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.75f))
                    )
                )
        )

        Text(
            text = anime?.title ?: "",
            style = MaterialTheme.typography.titleLarge.copy(color = Color.White),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}