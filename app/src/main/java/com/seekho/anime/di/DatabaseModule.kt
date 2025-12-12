package com.seekho.anime.di

import android.content.Context
import androidx.room.Room
import com.seekho.anime.data.local.AppDatabase
import com.seekho.anime.data.local.AnimeDao
import com.seekho.anime.data.local.MIGRATION_1_2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): AppDatabase =
        Room.databaseBuilder(ctx, AppDatabase::class.java, "seekho-db")
            .addMigrations(MIGRATION_1_2)
            .build()

    @Provides
    fun provideAnimeDao(db: AppDatabase): AnimeDao = db.animeDao()
}