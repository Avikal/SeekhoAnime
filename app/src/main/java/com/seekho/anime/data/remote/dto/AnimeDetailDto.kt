package com.seekho.anime.data.remote.dto

import com.seekho.anime.domain.model.CastMember

data class AnimeDetailDto(
    val mal_id: Int,
    val title: String,
    val synopsis: String?,
    val episodes: Int?,
    val score: Double?,
    val year: Int?,
    val images: ImagesDto?,
    val trailer: TrailerDto?,
    val genres: List<GenreDto>,
    val studios: List<StudioDto>,
    val cast: List<CastMember>
)