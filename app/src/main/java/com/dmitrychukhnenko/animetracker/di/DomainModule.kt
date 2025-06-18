package com.dmitrychukhnenko.animetracker.di

import com.dmitrychukhnenko.animetracker.domain.use_case.DeleteTitleUseCase
import com.dmitrychukhnenko.animetracker.domain.use_case.GetSavedTitlesUseCase
import com.dmitrychukhnenko.animetracker.domain.use_case.SaveTitleUseCase
import com.dmitrychukhnenko.animetracker.domain.use_case.SearchTitlesUseCase
import org.koin.dsl.module

val domainModule = module{
    factory { SearchTitlesUseCase(get()) }
    factory { GetSavedTitlesUseCase(get()) }
    factory { SaveTitleUseCase(get()) }
    factory { DeleteTitleUseCase(get()) }
}