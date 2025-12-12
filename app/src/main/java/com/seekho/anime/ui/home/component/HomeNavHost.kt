package com.seekho.anime.ui.home.component

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seekho.anime.ui.detail.component.AnimeDetailScreen

@Composable
fun HomeNavHost() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = "home") {
        composable("home") {
            HomeScreenPaging(onAnimeClick = { id -> nav.navigate("detail/$id") })
        }
        composable("detail/{id}") { back ->
            val id = back.arguments?.getString("id")?.toIntOrNull() ?: 0
            AnimeDetailScreen(navController = nav, animeId = id)
        }
    }
}