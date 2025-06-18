package com.dmitrychukhnenko.animetracker.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ImageUrlDto(
    @SerializedName("image_url") val imageUrl: String?
)