package com.seekho.anime.ui.detail.component.parts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seekho.anime.domain.model.Anime

@Composable
fun DetailInfoSection(
    anime: Anime?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        // --- Chips Row ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AssistChip(
                onClick = {},
                label = { Text("⭐ ${anime?.rating ?: "N/A"}") }
            )
            AssistChip(
                onClick = {},
                label = { Text("🎬 ${anime?.episodes ?: "?"} eps") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- Dynamic Scrollable Synopsis ---
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .weight(1f)                // 🎯 <-- dynamic height
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "Synopsis",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = anime?.synopsis ?: "No synopsis available.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}