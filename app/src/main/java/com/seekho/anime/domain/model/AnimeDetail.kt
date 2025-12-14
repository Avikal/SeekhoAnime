package com.seekho.anime.domain.model

data class AnimeDetail(
    val id: Int,
    val title: String,
    val synopsis: String?,
    val episodes: Int?,
    val rating: Double?,
    val year: Int?,
    val imageUrl: String,
    val trailerUrl: String?,
    val genres: List<String>,
    val studios: List<String>,
    val cast: List<CastMember>
)


data class CastMember(
    val name: String,
    val imageUrl: String,
    val role: String
)