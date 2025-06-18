package com.dmitrychukhnenko.animetracker.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dmitrychukhnenko.animetracker.domain.repository.TitleRepository
import com.dmitrychukhnenko.animetracker.domain.use_case.DeleteTitleUseCase
import com.dmitrychukhnenko.animetracker.domain.use_case.GetTitleDetailsUseCase
import com.dmitrychukhnenko.animetracker.domain.use_case.IsTitleSavedUseCase
import com.dmitrychukhnenko.animetracker.domain.use_case.SaveTitleUseCase

class DetailViewModelFactory(
    private val id: Int,
    private val repository: TitleRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(
                id,
                GetTitleDetailsUseCase(repository),
                SaveTitleUseCase(repository),
                DeleteTitleUseCase(repository),
                IsTitleSavedUseCase(repository)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}