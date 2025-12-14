package com.seekho.anime.domain.mapper

import com.seekho.anime.data.local.AnimeDetailEntity
import com.seekho.anime.data.remote.dto.AnimeDetailDto
import com.seekho.anime.domain.model.AnimeDetail
import com.seekho.anime.domain.model.CastMember

fun AnimeDetailDto.toEntity(): AnimeDetailEntity =
    AnimeDetailEntity(
        id = mal_id,
        title = title,
        synopsis = synopsis,
        episodes = episodes,
        rating = score,
        year = year,
        imageUrl = images?.jpg?.image_url ?: "",
        trailerUrl = trailer?.youtube_id?.let {
            "https://www.youtube.com/watch?v=$it"
        },
        genres = genres.joinToString(",") { it.name },
        studios = studios.joinToString(",") { it.name },
    )

fun AnimeDetailEntity.toDomain(cast: List<CastMember>): AnimeDetail =
    AnimeDetail(
        id = id,
        title = title,
        synopsis = synopsis,
        episodes = episodes,
        rating = rating,
        year = year,
        imageUrl = imageUrl,
        trailerUrl = trailerUrl,
        genres = genres.split(","),
        studios = studios.split(","),
        cast = cast
    )