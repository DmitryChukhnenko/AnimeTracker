package com.dmitrychukhnenko.animetracker.domain.use_case

import com.dmitrychukhnenko.animetracker.domain.repository.TitleRepository

class IsTitleSavedUseCase(private val repository: TitleRepository) {
    suspend operator fun invoke(id: Int) = repository.isTitleSaved(id)
}