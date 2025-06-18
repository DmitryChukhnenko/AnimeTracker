package com.dmitrychukhnenko.animetracker.domain.states

import com.dmitrychukhnenko.animetracker.domain.model.Title

sealed class ListState {
    object Loading : ListState()
    data class Success(val data: List<Title>) : ListState()
    data class Error(val message: String) : ListState()
    object Empty : ListState()
}