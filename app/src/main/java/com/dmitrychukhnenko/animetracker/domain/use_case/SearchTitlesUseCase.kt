package com.dmitrychukhnenko.animetracker.domain.use_case

import com.dmitrychukhnenko.animetracker.domain.model.Title
import com.dmitrychukhnenko.animetracker.domain.repository.TitleRepository

class SearchTitlesUseCase(private val repository: TitleRepository) {
    suspend operator fun invoke(query: String): List<Title> {
        val anime = repository.searchAnime(query)
        val manga = repository.searchManga(query)
        return (anime + manga).sortedByDescending { it.score ?: 0f }
    }
}