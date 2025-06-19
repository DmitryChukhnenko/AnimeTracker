package com.dmitrychukhnenko.animetracker.domain.model

data class Title(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val synopsis: String?,
    val genres: List<String>,
    val score: Float?
) {
    var isSaved: Boolean = false
}