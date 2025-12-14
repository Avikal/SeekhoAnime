package com.seekho.anime.ui.detail.component.parts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.seekho.anime.domain.model.CastMember

@Composable
fun CastSection(cast: List<CastMember>) {

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Main Cast", style = MaterialTheme.typography.titleMedium)

        LazyRow {
            items(cast.size) { index ->
                val member = cast[index]
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    AsyncImage(
                        model = member.imageUrl,
                        contentDescription = member.name,
                        modifier = Modifier
                            .size(72.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Text(member.name, style = MaterialTheme.typography.bodySmall)
                    Text(member.role, style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }
}