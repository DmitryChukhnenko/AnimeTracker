package com.dmitrychukhnenko.animetracker.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BaseSearchResponse<T>(
    @SerializedName("data") val data: List<T>?,
    @SerializedName("pagination") val pagination: PaginationDto?
)
