package com.luiz.mobile.mystudyapp

import androidx.multidex.MultiDexApplication
import com.luiz.mobile.mystudyapp.di.inject

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        inject()
    }
}