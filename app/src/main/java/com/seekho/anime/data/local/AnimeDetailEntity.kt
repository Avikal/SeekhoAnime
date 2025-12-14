package com.seekho.anime.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seekho.anime.domain.model.CastMember

@Entity(tableName = "anime_detail")
data class AnimeDetailEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val synopsis: String?,
    val episodes: Int?,
    val rating: Double?,
    val year: Int?,
    val imageUrl: String,
    val trailerUrl: String?,
    val genres: String,     // comma-separated
    val studios: String ,    // comma-separated
)