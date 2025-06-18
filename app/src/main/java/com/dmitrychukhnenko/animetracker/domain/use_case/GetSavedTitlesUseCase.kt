package com.dmitrychukhnenko.animetracker.domain.use_case

import com.dmitrychukhnenko.animetracker.domain.repository.TitleRepository

class GetSavedTitlesUseCase(private val repository: TitleRepository) {
    operator fun invoke() = repository.getSavedTitles()
}