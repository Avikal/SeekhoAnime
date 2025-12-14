package com.seekho.anime.ui.detail.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seekho.anime.common.Resource
import com.seekho.anime.domain.model.Anime
import com.seekho.anime.domain.model.AnimeDetail
import com.seekho.anime.domain.usecase.GetAnimeDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailState(val isLoading: Boolean = false, val anime: AnimeDetail? = null, val error: String? = null)

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(private val getAnimeDetailsUseCase: GetAnimeDetailsUseCase) : ViewModel() {
    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    fun load(id: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            when (val res = getAnimeDetailsUseCase(id)) {
                is Resource.Success -> _state.update { it.copy(isLoading = false, anime = res.data) }
                is Resource.Error -> _state.update { it.copy(isLoading = false, error = res.message) }
                else -> _state.update { it.copy(isLoading = false, error = "Unknown") }
            }
        }
    }
}