package com.seekho.anime.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.seekho.anime.data.paging.AnimePagingSource
import com.seekho.anime.data.remote.ApiService
import com.seekho.anime.domain.model.Anime
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopAnimePagedUseCase @Inject constructor(private val api: ApiService) {
    operator fun invoke(): Flow<PagingData<Anime>> {
        return Pager(PagingConfig(pageSize = 25)) { AnimePagingSource(api) }.flow
    }
}