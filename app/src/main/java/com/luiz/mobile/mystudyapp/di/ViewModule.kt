package com.luiz.mobile.mystudyapp.di

import com.luiz.mobile.mystudyapp.view.viewmodel.main.MainViewModel
import com.luiz.mobile.mystudyapp.view.viewmodel.star_wars.StarWarsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun viewModule() = module {
    viewModel {
        MainViewModel(repository = get())
    }
    viewModel {
        StarWarsViewModel(repository = get())
    }
}