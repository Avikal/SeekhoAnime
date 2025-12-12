package com.seekho.anime.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.seekho.anime.R
import com.seekho.anime.common.parsePagingError
import com.seekho.anime.ui.components.AppToolbar
import com.seekho.anime.ui.components.CommonErrorState
import com.seekho.anime.ui.home.viewModel.HomePagingViewModel
import com.seekho.anime.ui.home.component.parts.AnimeCard
import com.seekho.anime.ui.home.component.parts.LoadingShimmerItem
import com.seekho.anime.ui.theme.colorBackground

@Composable
fun HomeScreenPaging(
    onAnimeClick: (Int) -> Unit,
    vm: HomePagingViewModel = hiltViewModel()
) {
    val pagingItems = vm.pagingFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            AppToolbar(title = stringResource(R.string.top_anime))
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(color = colorBackground)
        ) {

            when (val state = pagingItems.loadState.refresh) {

                is LoadState.Loading -> {
                    LazyColumn {
                        items(6) { LoadingShimmerItem() }
                    }
                }

                is LoadState.Error -> {
                    CommonErrorState(
                        error = parsePagingError(state.error),
                        onRetry = { pagingItems.retry() }
                    )
                }

                else -> {
                    LazyColumn {

                        items(pagingItems.itemCount) { index ->
                            val anime = pagingItems[index]
                            if (anime != null)
                                AnimeCard(anime = anime, onClick = onAnimeClick)
                            else
                                LoadingShimmerItem()
                        }

                        when (val appendState = pagingItems.loadState.append) {

                            is LoadState.Loading -> item { LoadingShimmerItem() }

                            is LoadState.Error -> item {
                                CommonErrorState(
                                    error = parsePagingError(appendState.error),
                                    onRetry = { pagingItems.retry() }
                                )
                            }

                            else -> {}
                        }
                    }
                }
            }
        }
    }
}