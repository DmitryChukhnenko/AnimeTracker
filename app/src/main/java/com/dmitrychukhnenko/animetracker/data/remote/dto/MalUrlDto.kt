package com.dmitrychukhnenko.animetracker.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MalUrlDto(
    @SerializedName("mal_id") val malId: Int,
    @SerializedName("name") val name: String
)
fun List<MalUrlDto>?.toGenreList(): List<String> {
    return this?.map { it.name } ?: emptyList()
}