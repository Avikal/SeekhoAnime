package com.seekho.anime.domain.repository

import com.seekho.anime.common.Resource
import com.seekho.anime.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getTopAnime(): Flow<Resource<List<Anime>>>
    suspend fun getAnimeDetails(id: Int): Resource<Anime>
}