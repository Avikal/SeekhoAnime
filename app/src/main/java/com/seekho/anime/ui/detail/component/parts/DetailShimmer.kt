package com.seekho.anime.ui.detail.component.parts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
fun DetailShimmer() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .placeholder(visible = true, highlight = PlaceholderHighlight.shimmer())
        ) {}

        repeat(3) {
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .placeholder(visible = true, highlight = PlaceholderHighlight.shimmer())
            ) {}
        }
    }
}