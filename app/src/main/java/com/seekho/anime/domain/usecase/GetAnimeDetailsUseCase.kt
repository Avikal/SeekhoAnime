package com.seekho.anime.domain.usecase

import com.seekho.anime.common.Resource
import com.seekho.anime.domain.model.Anime
import com.seekho.anime.domain.repository.AnimeRepository
import javax.inject.Inject

class GetAnimeDetailsUseCase @Inject constructor(private val repo: AnimeRepository) {
    suspend operator fun invoke(id: Int): Resource<Anime> = repo.getAnimeDetails(id)
}