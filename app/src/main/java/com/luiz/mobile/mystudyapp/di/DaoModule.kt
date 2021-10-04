package com.luiz.mobile.mystudyapp.di

import com.luiz.mobile.mystudyapp.data.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun daoModule() = module {
    single { AppDatabase.getInstance(androidContext()).contactDao }
}