package com.seekho.anime.ui.home.component.parts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
fun LoadingShimmerItem() {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(260.dp)
            .placeholder(
                visible = true,
                highlight = PlaceholderHighlight.shimmer()
            )
    ) {}
}