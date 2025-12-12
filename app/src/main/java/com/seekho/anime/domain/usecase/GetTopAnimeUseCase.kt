package com.seekho.anime.domain.usecase

import com.seekho.anime.common.Resource
import com.seekho.anime.domain.model.Anime
import com.seekho.anime.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopAnimeUseCase @Inject constructor(private val repo: AnimeRepository) {
    operator fun invoke(): Flow<Resource<List<Anime>>> = repo.getTopAnime()
}