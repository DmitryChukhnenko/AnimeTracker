package com.dmitrychukhnenko.animetracker.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("jpg") val jpg: ImageUrlDto?
)