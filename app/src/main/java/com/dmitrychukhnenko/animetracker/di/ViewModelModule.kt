package com.dmitrychukhnenko.animetracker.di

import com.dmitrychukhnenko.animetracker.domain.model.Title
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import com.dmitrychukhnenko.animetracker.presentation.detail.DetailViewModel
import com.dmitrychukhnenko.animetracker.presentation.search.SearchViewModel
import com.dmitrychukhnenko.animetracker.presentation.list.ListViewModel

val viewModelModule = module {
    viewModel { SearchViewModel(get()) }
    viewModel { ListViewModel(get()) }
    viewModel { (id: Int) ->
        DetailViewModel(
            id = id,
            getTitleDetails = get(),
            saveTitle = get(),
            deleteTitle = get(),
            isTitleSaved = get()
        )
    }
}