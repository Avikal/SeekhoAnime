package com.seekho.anime.data.remote.dto

data class CharacterResponse(val data: List<CharacterRoleDto>)

data class CharacterRoleDto(
    val character: CharacterDto,
    val role: String
)

data class CharacterDto(
    val name: String,
    val images: CharacterImagesDto
)

data class CharacterImagesDto(
    val jpg: CharacterImageTypeDto
)

data class CharacterImageTypeDto(val image_url: String?)
