package com.seekho.anime.ui.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.seekho.anime.domain.model.Anime
import com.seekho.anime.domain.usecase.GetTopAnimePagedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomePagingViewModel @Inject constructor(private val getTopPaged: GetTopAnimePagedUseCase) : ViewModel() {
    val pagingFlow: Flow<PagingData<Anime>> =
        getTopPaged().cachedIn(viewModelScope)
}