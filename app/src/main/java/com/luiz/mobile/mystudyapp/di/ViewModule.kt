package com.luiz.mobile.mystudyapp.di

import com.luiz.mobile.mystudyapp.view.viewmodel.main.MainViewModel
import com.luiz.mobile.mystudyapp.view.viewmodel.rick_morty.RickMortyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun viewModule() = module {
    viewModel {
        MainViewModel(repository = get())
    }
    viewModel {
        RickMortyViewModel(repository = get())
    }
}