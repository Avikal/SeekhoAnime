package com.seekho.anime.di

import com.seekho.anime.data.local.AnimeDao
import com.seekho.anime.data.remote.ApiService
import com.seekho.anime.data.repository.AnimeRepositoryImpl
import com.seekho.anime.domain.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAnimeRepository(api: ApiService, dao: AnimeDao): AnimeRepository =
        AnimeRepositoryImpl(api, dao)
}