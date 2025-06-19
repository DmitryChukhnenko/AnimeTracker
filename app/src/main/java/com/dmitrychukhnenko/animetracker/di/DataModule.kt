package com.dmitrychukhnenko.animetracker.di

import androidx.room.Room
import com.dmitrychukhnenko.animetracker.data.local.database.AppDatabase
import com.dmitrychukhnenko.animetracker.data.remote.api.JikanApiService
import com.dmitrychukhnenko.animetracker.data.repository.TitleRepositoryImpl
import com.dmitrychukhnenko.animetracker.domain.repository.TitleRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    single<TitleRepository> { TitleRepositoryImpl(get(), get()) }
    single<JikanApiService> { get<Retrofit>().create(JikanApiService::class.java) }
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "app-db").build() }
    single { get<AppDatabase>().titleDao() }
}