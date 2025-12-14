package com.seekho.anime.data.repository

import com.seekho.anime.common.Resource
import com.seekho.anime.common.network.NetworkChecker
import com.seekho.anime.data.local.AnimeDao
import com.seekho.anime.data.local.AnimeEntity
import com.seekho.anime.data.remote.ApiService
import com.seekho.anime.domain.mapper.toDomain
import com.seekho.anime.domain.mapper.toEntity
import com.seekho.anime.domain.model.Anime
import com.seekho.anime.domain.model.AnimeDetail
import com.seekho.anime.domain.model.CastMember
import com.seekho.anime.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: AnimeDao,
    private val networkChecker: NetworkChecker
) : AnimeRepository {

    // -------------------------
    // TOP ANIME (LIST)
    // -------------------------
    override fun getTopAnime(): Flow<Resource<List<Anime>>> = flow {

        emit(Resource.Loading())

        // 1️⃣ Emit cached data first
        val cached = dao.getAll()
            .firstOrNull()
            ?.map { it.toDomain() }
            .orEmpty()

        if (cached.isNotEmpty()) {
            emit(Resource.Success(cached))
        }

        // 2️⃣ Fetch remote
        if (!networkChecker.isConnected()) {
            emit(Resource.Error("NO_INTERNET", cached))
            return@flow
        }

        try {
            val response = api.getTopAnime(page = 1)

            val mapped = response.data.map { dto ->
                Anime(
                    id = dto.mal_id,
                    title = dto.title,
                    episodes = dto.episodes,
                    rating = dto.score,
                    imageUrl = dto.images?.jpg?.image_url ?: ""
                )
            }

            // 3️⃣ Cache list
            dao.insertAll(mapped.map { it.toEntity() })

            emit(Resource.Success(mapped))

        } catch (e: Exception) {
            emit(Resource.Error("Network error: ${e.localizedMessage}", cached))
        }

    }.catch { e ->
        emit(Resource.Error("Unknown error: ${e.localizedMessage}"))
    }

    // -------------------------
    // ANIME DETAIL
    // -------------------------
    override suspend fun getAnimeDetails(id: Int): Resource<AnimeDetail> {

        if (!networkChecker.isConnected()) {
            return Resource.Error("NO_INTERNET")
        }

        val detail = api.getAnimeDetails(id).data
        val characters = api.getAnimeCharacters(id).data.take(10)
        try {
            return Resource.Success(
                AnimeDetail(
                    id = detail.mal_id,
                    title = detail.title,
                    synopsis = detail.synopsis,
                    episodes = detail.episodes,
                    rating = detail.score,
                    year = detail.year,
                    imageUrl = detail.images?.jpg?.image_url ?: "",
                    trailerUrl = detail.trailer?.youtube_id?.let { "https://www.youtube.com/watch?v=$it" },
                    genres = detail.genres.map { it.name },
                    studios = detail.studios.map { it.name },
                    cast = characters.map {
                        CastMember(
                            name = it.character.name,
                            imageUrl = it.character.images.jpg.image_url ?: "",
                            role = it.role
                        )
                    }
                )
            )

        } catch (e: Exception) {
            return Resource.Error("ERROR: ${e.localizedMessage}")
        }
    }
}