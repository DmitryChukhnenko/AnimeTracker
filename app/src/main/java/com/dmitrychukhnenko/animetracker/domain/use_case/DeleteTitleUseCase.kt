package com.dmitrychukhnenko.animetracker.domain.use_case

import com.dmitrychukhnenko.animetracker.domain.model.Title
import com.dmitrychukhnenko.animetracker.domain.repository.TitleRepository

class DeleteTitleUseCase(private val repository: TitleRepository) {
    suspend operator fun invoke(title: Title) = repository.deleteTitle(title)
}