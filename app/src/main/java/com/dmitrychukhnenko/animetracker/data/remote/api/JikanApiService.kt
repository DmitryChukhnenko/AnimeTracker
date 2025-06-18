package com.dmitrychukhnenko.animetracker.data.remote.api

import com.dmitrychukhnenko.animetracker.data.remote.dto.AnimeDto
import com.dmitrychukhnenko.animetracker.data.remote.dto.BaseDetailsResponse
import com.dmitrychukhnenko.animetracker.data.remote.dto.BaseSearchResponse
import com.dmitrychukhnenko.animetracker.data.remote.dto.MangaDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JikanApiService {
    @GET("anime")
    suspend fun searchAnime(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): BaseSearchResponse<AnimeDto>

    @GET("anime/{id}")
    suspend fun getAnimeById(
        @Path("id") id: Int
    ): BaseDetailsResponse<AnimeDto>

    @GET("manga")
    suspend fun searchManga(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): BaseSearchResponse<MangaDto>

    @GET("manga/{id}")
    suspend fun getMangaById(
        @Path("id") id: Int
    ): BaseDetailsResponse<MangaDto>
}