package com.seekho.anime.data.remote.dto

data class TrailerDto(
    val youtube_id: String?,
    val url: String?,
    val embed_url: String?,
    val images: TrailerImagesDto?
)

data class TrailerImagesDto(
    val image_url: String?,
    val small_image_url: String?,
    val medium_image_url: String?,
    val large_image_url: String?,
    val maximum_image_url: String?
)