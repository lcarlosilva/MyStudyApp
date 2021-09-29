package com.luiz.mobile.mystudyapp.di

import com.luiz.mobile.mystudyapp.view.viewmodel.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun viewModule() = module {
    viewModel {
        MainViewModel(repository = get())
    }
}