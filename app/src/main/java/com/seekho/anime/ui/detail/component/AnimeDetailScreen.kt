package com.seekho.anime.ui.detail.component

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.seekho.anime.R
import com.seekho.anime.ui.components.AppToolbar
import com.seekho.anime.ui.components.CommonErrorState
import com.seekho.anime.ui.detail.component.parts.CastSection
import com.seekho.anime.ui.detail.component.parts.DetailHeader
import com.seekho.anime.ui.detail.component.parts.DetailInfoSection
import com.seekho.anime.ui.detail.component.parts.DetailShimmer
import com.seekho.anime.ui.detail.component.parts.GenreChips
import com.seekho.anime.ui.detail.component.parts.TrailerPlayer
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
                title = state.anime?.title ?: "Details",
                showBack = true,
                onBack = { navController.popBackStack() }
            )
        }
    ) { padding ->

        when {
            state.isLoading -> DetailShimmer()
            state.error != null -> CommonErrorState(state.error!!) { vm.load(animeId) }
            state.anime != null -> {
                val anime = state.anime!!

                LazyColumn(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {

                    item {
                        Log.e("Indore", anime.trailerUrl ?: "No URL")
                        TrailerPlayer(anime.trailerUrl)
                    }

                    item {
                        DetailHeader(anime)
                    }

                    item {
                        DetailInfoSection(anime)
                    }

                    item {
                        GenreChips(anime.genres)
                    }

                    item {
                        CastSection(anime.cast)
                    }
                }
            }
        }
    }
}