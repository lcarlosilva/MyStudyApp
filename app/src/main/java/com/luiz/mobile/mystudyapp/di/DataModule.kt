package com.luiz.mobile.mystudyapp.di

import com.luiz.mobile.mystudyapp.data.source.MainDataSource
import com.luiz.mobile.mystudyapp.data.source.RickMortyDataSource
import com.luiz.mobile.mystudyapp.domain.repository.MainRepository
import com.luiz.mobile.mystudyapp.domain.repository.RickMortyRepository
import org.koin.dsl.module

fun dataModule() = module {
    factory<MainRepository> { MainDataSource() }
    factory<RickMortyRepository> { RickMortyDataSource(get()) }
}