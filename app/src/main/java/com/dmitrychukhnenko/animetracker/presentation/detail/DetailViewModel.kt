package com.dmitrychukhnenko.animetracker.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmitrychukhnenko.animetracker.domain.model.Title
import com.dmitrychukhnenko.animetracker.domain.use_case.DeleteTitleUseCase
import com.dmitrychukhnenko.animetracker.domain.use_case.GetTitleDetailsUseCase
import com.dmitrychukhnenko.animetracker.domain.use_case.IsTitleSavedUseCase
import com.dmitrychukhnenko.animetracker.domain.use_case.SaveTitleUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    private val id: Int,
    private val getTitleDetails: GetTitleDetailsUseCase,
    private val saveTitle: SaveTitleUseCase,
    private val deleteTitle: DeleteTitleUseCase,
    private val isTitleSaved: IsTitleSavedUseCase
) : ViewModel() {
    private val _titleDetails = MutableLiveData<Title?>()
    val titleDetails: LiveData<Title?> = _titleDetails

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    private val _errorState = MutableLiveData<String?>()
    val errorState: LiveData<String?> = _errorState

    init {
        loadTitleDetails()
    }

    fun loadTitleDetails() {
        _loadingState.value = true
        viewModelScope.launch {
            try {
                val details = getTitleDetails(id)
                _titleDetails.postValue(details)
            } catch (e: Exception) {
                _errorState.postValue(e.message ?: "Unknown error")
            } finally {
                _loadingState.postValue(false)
            }
        }
    }

    fun toggleSaveStatus() {
        viewModelScope.launch {
            try {
                val currentTitle = _titleDetails.value ?: return@launch
                if (currentTitle.isSaved) {
                    deleteTitle(currentTitle)
                    val c = currentTitle.copy()
                    c.isSaved = false
                    _titleDetails.postValue(c)
                } else {
                    saveTitle(currentTitle)
                    val c = currentTitle.copy()
                    c.isSaved = true
                    _titleDetails.postValue(c)
                }
            } catch (e: Exception) {
                _errorState.postValue("Failed to toggle save status")
            }
        }
    }
}