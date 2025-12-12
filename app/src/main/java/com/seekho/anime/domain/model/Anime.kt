package com.seekho.anime.domain.model

import com.seekho.anime.data.remote.ImageDto

data class Anime(
    val id: Int,
    val title: String,
    val episodes: Int?,
    val rating: Double?,
    val imageUrl: String,
    val synopsis: String
)