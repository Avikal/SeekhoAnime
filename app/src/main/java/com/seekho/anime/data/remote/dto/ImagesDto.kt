package com.seekho.anime.data.remote.dto

data class ImagesDto(
    val jpg: ImageTypeDto?,
    val webp: ImageTypeDto?
)

data class ImageTypeDto(
    val image_url: String?,
    val small_image_url: String?,
    val large_image_url: String?
)