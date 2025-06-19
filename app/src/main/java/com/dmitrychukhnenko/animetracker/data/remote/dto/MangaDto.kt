package com.dmitrychukhnenko.animetracker.data.remote.dto

import com.dmitrychukhnenko.animetracker.domain.model.Title
import com.google.gson.annotations.SerializedName

data class MangaDto(
    @SerializedName("mal_id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("images") val images: ImageDto?,
    @SerializedName("synopsis") val synopsis: String?,
    @SerializedName("score") val score: Float?,
    @SerializedName("genres") val genres: List<MalUrlDto>?,
    @SerializedName("explicit_genres") val explicitGenres: List<MalUrlDto>?,
    @SerializedName("themes") val themes: List<MalUrlDto>?,
    @SerializedName("demographics") val demographics: List<MalUrlDto>?
) {
    fun toTitle(): Title {
        return Title(
            id = id,
            title = title ?: "Unknown Manga",
            imageUrl = images?.jpg?.imageUrl ?: "",
            synopsis = synopsis,
            score = score,
            genres = combineGenres()
        )
    }

    private fun combineGenres(): List<String> {
        return (genres.toGenreList() +
                explicitGenres.toGenreList() +
                themes.toGenreList() +
                demographics.toGenreList()).distinct()
    }
}