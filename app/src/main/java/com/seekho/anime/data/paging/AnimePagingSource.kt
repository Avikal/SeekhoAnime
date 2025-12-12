package com.seekho.anime.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.seekho.anime.data.remote.ApiService
import com.seekho.anime.domain.model.Anime
import retrofit2.HttpException
import java.io.IOException

class AnimePagingSource(private val api: ApiService) : PagingSource<Int, Anime>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        val page = params.key ?: 1
        return try {
            val resp = api.getTopAnime(page)
            val items = resp.data.map {
                Anime(
                    id = it.mal_id,
                    title = it.title,
                    episodes = it.episodes,
                    rating = it.score,
                    imageUrl = it.images?.get("jpg")?.image_url ?: "",
                    synopsis = it.synopsis ?: "")
            }
            val nextKey = if (resp.pagination?.has_next_page == true) page + 1 else null
            LoadResult.Page(items, prevKey = if (page == 1) null else page - 1, nextKey = nextKey)
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        return state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }
    }
}