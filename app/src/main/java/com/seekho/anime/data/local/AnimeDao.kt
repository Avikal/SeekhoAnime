package com.seekho.anime.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime")
    fun getAll(): Flow<List<AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<AnimeEntity>)

    // DETAIL
    @Query("SELECT * FROM anime_detail WHERE id = :id")
    suspend fun getDetail(id: Int): AnimeDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(entity: AnimeDetailEntity)
}