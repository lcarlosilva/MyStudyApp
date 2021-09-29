package com.luiz.mobile.mystudyapp.view.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luiz.mobile.mystudyapp.domain.repository.MainRepository

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val infosLiveData: MutableLiveData<String> = MutableLiveData()

    fun getInfosDevices() {
        repository.getDevicesInfos() { devicesInfo ->
            infosLiveData.postValue(devicesInfo)
        }
    }

}