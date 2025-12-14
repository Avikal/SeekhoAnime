package com.seekho.anime.domain.mapper

import com.seekho.anime.data.local.AnimeEntity
import com.seekho.anime.data.remote.dto.AnimeListDto
import com.seekho.anime.domain.model.Anime

// LIST
fun AnimeEntity.toDomain(): Anime =
    Anime(
        id = id,
        title = title,
        episodes = episodes,
        rating = rating,
        imageUrl = imageUrl
    )

fun Anime.toEntity(): AnimeEntity =
    AnimeEntity(
        id = id,
        title = title,
        episodes = episodes,
        rating = rating,
        imageUrl = imageUrl
    )