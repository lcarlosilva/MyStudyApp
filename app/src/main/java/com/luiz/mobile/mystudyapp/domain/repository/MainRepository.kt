package com.luiz.mobile.mystudyapp.domain.repository

interface MainRepository {
    fun getDevicesInfos(result: (infosDevices: String) -> Unit)
}

