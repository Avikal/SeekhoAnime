package com.seekho.anime.data.repository

import com.seekho.anime.common.Resource
import com.seekho.anime.data.local.AnimeDao
import com.seekho.anime.data.local.AnimeEntity
import com.seekho.anime.data.remote.ApiService
import com.seekho.anime.domain.model.Anime
import com.seekho.anime.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: AnimeDao
) : AnimeRepository {

    override fun getTopAnime(): Flow<Resource<List<Anime>>> = flow {
        emit(Resource.Loading())
        val cached = try {
            dao.getAll().first().map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
        if (cached.isNotEmpty()) emit(Resource.Success(cached))

        try {
            val resp = api.getTopAnime()
            val mapped = resp.data.map {
                Anime(
                    id = it.mal_id,
                    title = it.title,
                    episodes = it.episodes,
                    rating = it.score,
                    imageUrl = it.images?.get("jpg")?.image_url ?: "",
                    synopsis = it.synopsis ?: ""
                )
            }
            dao.insertAll(mapped.map { it.toEntity() })
            emit(Resource.Success(mapped))
        } catch (e: Exception) {
            emit(Resource.Error("Network error: ${e.localizedMessage}", cached))
        }
    }.catch { e -> emit(Resource.Error("Unknown error: ${e.localizedMessage}")) }

    override suspend fun getAnimeDetails(id: Int) = try {
        val resp = api.getAnimeDetails(id)
        val dto = resp.data

        Resource.Success(
            Anime(
                id = dto.mal_id,
                title = dto.title,
                episodes = dto.episodes,
                rating = dto.score,
                imageUrl = dto.images?.get("jpg")?.image_url ?: "",
                synopsis = dto.synopsis ?: ""
            )
        )
    } catch (e: Exception) {
        Resource.Error("Failed to load details: ${e.localizedMessage}")
    }
}

// Helpers
private fun AnimeEntity.toDomain() = Anime(id, title, episodes, rating, imageUrl, synopsis)
private fun Anime.toEntity() = AnimeEntity(id, title, episodes, rating, imageUrl, synopsis = synopsis)