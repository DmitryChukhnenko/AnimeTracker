package com.dmitrychukhnenko.animetracker.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmitrychukhnenko.animetracker.domain.states.SearchState
import com.dmitrychukhnenko.animetracker.domain.use_case.SearchTitlesUseCase
import kotlinx.coroutines.launch

class SearchViewModel(
    private val _searchTitles: SearchTitlesUseCase
) : ViewModel() {
    private val _searchState = MutableLiveData<SearchState>()
    val searchState: LiveData<SearchState> = _searchState

    fun searchTitles(query: String) {
        _searchState.value = SearchState.Loading
        viewModelScope.launch {
            try {
                val results = _searchTitles(query)
                _searchState.postValue(SearchState.Success(results))
            } catch (e: Exception) {
                _searchState.postValue(SearchState.Error(e.message ?: "Unknown error"))
            }
        }
    }
}