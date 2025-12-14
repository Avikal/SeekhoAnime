package com.seekho.anime.data.remote.dto

data class DetailResponse<T>(val data: T)
data class ListResponse<T>(val data: List<T>, val pagination: PaginationDto?)
data class PaginationDto(val last_visible_page: Int, val has_next_page: Boolean)
