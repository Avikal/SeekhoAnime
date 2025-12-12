package com.seekho.anime.ui.home.component.parts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.seekho.anime.R
import com.seekho.anime.domain.model.Anime

@Composable
fun AnimeCard(anime: Anime, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clickable { onClick(anime.id) },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

        Column {

            AsyncImage(
                model = anime.imageUrl,
                contentDescription = anime.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RectangleShape),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(12.dp)) {

                Text(
                    text = anime.title,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.rating, anime.rating ?: "?"),
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = stringResource(R.string.episodes, anime.episodes ?: "?"),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}