package com.luiz.mobile.mystudyapp.di

import com.google.gson.GsonBuilder
import com.luiz.mobile.mystudyapp.BuildConfig
import com.luiz.mobile.mystudyapp.data.interceptor.AuthInterceptor
import com.luiz.mobile.mystudyapp.data.service.StarWarsService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

private const val TIMEOUT: Long = 30L

private val API_URL: StringQualifier = StringQualifier("API_URL")

fun networkModule() = module {
    factory { provideGson() }
    factory { provideOkhttpClient(getAll()) }
    factory(qualifier = API_URL) { provideApiUrlStarWars() }
    factory { AuthInterceptor() } bind Interceptor::class
    factory { provideLoggingInterceptor() } bind Interceptor::class
    factory<Converter.Factory> { GsonConverterFactory.create(get()) }

    single { provideRetrofit(get(API_URL), get(), get()) }
    single { get<Retrofit>().create<StarWarsService>() }
}

private fun provideApiUrlStarWars() = BuildConfig.API_STAR_WARS

private fun provideGson() = GsonBuilder().create()

private fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
    if (BuildConfig.DEBUG) {
        this.level = HttpLoggingInterceptor.Level.BODY
    } else {
        this.level = HttpLoggingInterceptor.Level.NONE
    }
}

private fun provideOkhttpClient(interceptors: List<Interceptor>): OkHttpClient {
    return OkHttpClient.Builder().run {
        readTimeout(TIMEOUT, TimeUnit.SECONDS)
        connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        interceptors.forEach(::addInterceptor)
        build()
    }
}

private fun provideRetrofit(baseUrl: String, client: OkHttpClient,
                            converter: Converter.Factory): Retrofit {
    return Retrofit.Builder().run {
        baseUrl(baseUrl)
        client(client)
        addConverterFactory(converter)
        build()
    }
}