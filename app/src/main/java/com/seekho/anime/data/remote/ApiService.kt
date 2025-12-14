package com.seekho.anime.data.remote

import com.seekho.anime.data.remote.dto.AnimeDetailDto
import com.seekho.anime.data.remote.dto.AnimeListDto
import com.seekho.anime.data.remote.dto.CharacterResponse
import com.seekho.anime.data.remote.dto.DetailResponse
import com.seekho.anime.data.remote.dto.ListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("top/anime")
    suspend fun getTopAnime(@Query("page") page: Int): ListResponse<AnimeListDto>

    @GET("anime/{id}")
    suspend fun getAnimeDetails(@Path("id") id: Int): DetailResponse<AnimeDetailDto>

    @GET("anime/{id}/characters")
    suspend fun getAnimeCharacters(@Path("id") id: Int): CharacterResponse
}