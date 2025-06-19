package com.dmitrychukhnenko.animetracker.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BaseDetailsResponse<T>(
    @SerializedName("data") val data: T?
)