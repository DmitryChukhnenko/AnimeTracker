package com.dmitrychukhnenko.animetracker.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmitrychukhnenko.animetracker.domain.states.ListState
import com.dmitrychukhnenko.animetracker.domain.use_case.GetSavedTitlesUseCase
import kotlinx.coroutines.launch

class ListViewModel(
    private val getSavedTitles: GetSavedTitlesUseCase
) : ViewModel() {
    private val _listState = MutableLiveData<ListState>()
    val listState: LiveData<ListState> = _listState

    fun loadTitles() {
        _listState.value = ListState.Loading
        viewModelScope.launch {
            try {
                getSavedTitles().collect { titles ->
                    if (titles.isEmpty()) {
                        _listState.postValue(ListState.Empty)
                    } else {
                        _listState.postValue(ListState.Success(titles))
                    }
                }
            } catch (e: Exception) {
                _listState.postValue(ListState.Error(e.message ?: "Unknown error"))
            }
        }
    }
}
