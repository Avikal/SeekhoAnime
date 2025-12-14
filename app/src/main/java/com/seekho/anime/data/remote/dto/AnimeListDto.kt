package com.seekho.anime.data.remote.dto

data class AnimeListDto(
    val mal_id: Int,
    val title: String,
    val images: ImagesDto?,
    val score: Double?,
    val episodes: Int?
)
