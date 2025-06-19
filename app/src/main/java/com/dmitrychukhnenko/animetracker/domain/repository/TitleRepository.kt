package com.dmitrychukhnenko.animetracker.domain.repository

import com.dmitrychukhnenko.animetracker.domain.model.Title
import kotlinx.coroutines.flow.Flow

interface TitleRepository {
    suspend fun searchAnime(query: String): List<Title>
    suspend fun searchManga(query: String): List<Title>
    fun getSavedTitles(): Flow<List<Title>>
    suspend fun getTitleDetails(id: Int): Title
    suspend fun saveTitle(title: Title)
    suspend fun deleteTitle(title: Title)
    suspend fun isTitleSaved(id: Int): Boolean
}