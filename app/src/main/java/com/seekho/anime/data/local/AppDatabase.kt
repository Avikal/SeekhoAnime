package com.seekho.anime.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AnimeEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}