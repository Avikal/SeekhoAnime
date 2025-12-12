package com.seekho.anime.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

data class TopResponse<T>(val data: List<T>, val pagination: PaginationDto?)
data class PaginationDto(val current_page: Int?, val has_next_page: Boolean?)

data class DetailResponse<T>(
    val data: T
)
data class AnimeDto(
    val mal_id: Int,
    val title: String,
    val images: Map<String, ImageDto>?,
    val episodes: Int?,
    val score: Double?,
    val synopsis: String?,
)
data class AnimeDetailDto(
    val mal_id: Int,
    val title: String,
    val synopsis: String?,
    val episodes: Int?,
    val score: Double?,
    val year: Int?,
    val images: Map<String, ImageDto>?,
)

data class ImageDto(
    val image_url: String
)

data class ImageTypeDto(
    val image_url: String,
    val small_image_url: String,
    val large_image_url: String
)

interface ApiService {
    @GET("top/anime")
    suspend fun getTopAnime(@Query("page") page: Int = 1): TopResponse<AnimeDto>

    @GET("anime/{id}")
    suspend fun getAnimeDetails(@Path("id") id: Int): DetailResponse<AnimeDetailDto>
}