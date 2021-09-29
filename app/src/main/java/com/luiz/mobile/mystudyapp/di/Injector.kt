package com.luiz.mobile.mystudyapp.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun Application.inject() {
    startKoin {
        androidLogger()
        androidContext(this@inject)
        modules(getModules())
    }
}

fun getModules() = listOf(
    viewModule(),
    dataModule()
)
