package com.dmitrychukhnenko.animetracker.data.repository

import com.dmitrychukhnenko.animetracker.data.local.dao.TitleDao
import com.dmitrychukhnenko.animetracker.data.local.entity.TitleEntity
import com.dmitrychukhnenko.animetracker.data.remote.api.JikanApiService
import com.dmitrychukhnenko.animetracker.data.remote.dto.AnimeDto
import com.dmitrychukhnenko.animetracker.domain.model.Title
import com.dmitrychukhnenko.animetracker.domain.repository.TitleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TitleRepositoryImpl(
    private val api: JikanApiService,
    private val dao: TitleDao
) : TitleRepository {

    override suspend fun searchAnime(query: String): List<Title> {
        return api.searchAnime(query).data?.map { it.toTitle() } ?: emptyList()
    }

    override suspend fun searchManga(query: String): List<Title> {
        return api.searchManga(query).data?.map { it.toTitle() } ?: emptyList()
    }

    override suspend fun getTitleDetails(id: Int): Title {
        val localTitle = dao.getTitleById(id)?.toTitle()
        if (localTitle != null) {
            localTitle.isSaved = true
            return localTitle
        }

        val animeResponse = runCatching { api.getAnimeById(id) }.getOrNull()
        animeResponse?.data?.let {
            return it.toTitle().apply { isSaved = false }
        }

        val mangaResponse = runCatching { api.getMangaById(id) }.getOrNull()
        mangaResponse?.data?.let {
            return it.toTitle().apply { isSaved = false }
        }

        throw Exception("Title not found")
    }

    override fun getSavedTitles(): Flow<List<Title>> {
        return dao.getAllTitles().map { list -> list.map { it.toTitle().apply { isSaved = true } } }
    }

    override suspend fun saveTitle(title: Title) {
        dao.insertTitle(title.toEntity())
    }

    override suspend fun deleteTitle(title: Title) {
        dao.deleteTitle(title.toEntity())
    }

    override suspend fun isTitleSaved(id: Int): Boolean {
        return dao.getTitleById(id) != null
    }
}

private fun Title.toEntity() = TitleEntity(
    id = id,
    title = title,
    imageUrl = imageUrl,
    synopsis = synopsis,
    score = score,
    genres = genres.joinToString(",")
)

private fun TitleEntity.toTitle() = Title(
    id = id,
    title = title,
    imageUrl = imageUrl,
    synopsis = synopsis,
    score = score,
    genres = genres.split(",")
)