package com.luiz.mobile.mystudyapp.di

import com.luiz.mobile.mystudyapp.data.source.MainDataSource
import com.luiz.mobile.mystudyapp.domain.repository.MainRepository
import org.koin.dsl.module


fun dataModule() = module {
    factory<MainRepository> { MainDataSource() }
}