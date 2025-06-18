package com.dmitrychukhnenko.animetracker.domain.states

import com.dmitrychukhnenko.animetracker.domain.model.Title

sealed class SearchState {
    data object Loading : SearchState()
    data class Success(val data: List<Title>) : SearchState()
    data class Error(val message: String) : SearchState()
}