package com.dmitrychukhnenko.animetracker.domain.use_case

import com.dmitrychukhnenko.animetracker.domain.model.Title
import com.dmitrychukhnenko.animetracker.domain.repository.TitleRepository

class GetTitleDetailsUseCase(private val repository: TitleRepository) {
    suspend operator fun invoke(id: Int): Title {
        return repository.getTitleDetails(id)
    }
}