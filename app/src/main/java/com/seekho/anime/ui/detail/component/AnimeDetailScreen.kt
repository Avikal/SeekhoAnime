package com.seekho.anime.ui.detail.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.seekho.anime.R
import com.seekho.anime.ui.components.AppToolbar
import com.seekho.anime.ui.components.CommonErrorState
import com.seekho.anime.ui.detail.component.parts.DetailHeader
import com.seekho.anime.ui.detail.component.parts.DetailInfoSection
import com.seekho.anime.ui.detail.component.parts.DetailShimmer
import com.seekho.anime.ui.detail.viewModel.AnimeDetailViewModel

@Composable
fun AnimeDetailScreen(
    navController: NavController,
    animeId: Int,
    vm: AnimeDetailViewModel = hiltViewModel()
) {
    val state by vm.state.collectAsState()

    LaunchedEffect(animeId) { vm.load(animeId) }

    Scaffold(
        topBar = {
            AppToolbar(
                title = state.anime?.title ?: stringResource(R.string.anime_details),
                showBack = true,
                onBack = { navController.popBackStack() }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            when {

                state.isLoading -> DetailShimmer()

                state.error != null -> {
                    CommonErrorState(
                        error = state.error,
                        onRetry = { vm.load(animeId) }
                    )
                }

                state.anime != null -> {
                    Column {
                        DetailHeader(anime = state.anime)
                        DetailInfoSection(anime = state.anime, modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}