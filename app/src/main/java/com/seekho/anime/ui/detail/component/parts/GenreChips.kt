package com.seekho.anime.ui.detail.component.parts

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun GenreChips(genres: List<String>) {
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        genres.forEach { genre ->
            AssistChip(
                onClick = {},
                label = { Text(genre) },
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}