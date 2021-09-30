package com.luiz.mobile.mystudyapp.view.viewmodel.star_wars

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luiz.mobile.mystudyapp.domain.entity.Species
import com.luiz.mobile.mystudyapp.domain.repository.StarWarsRepository
import com.luiz.mobile.mystudyapp.domain.result.BaseResult

class StarWarsViewModel(private val repository: StarWarsRepository) : ViewModel() {

    val successLiveData: MutableLiveData<ArrayList<Species>> = MutableLiveData()
    val networkErrorLiveData: MutableLiveData<String> = MutableLiveData()
    val serverErrorLiveData: MutableLiveData<String> = MutableLiveData()

    fun species() {
        repository.species { result ->
            when (result) {
                is BaseResult.Success -> {
                    successLiveData.postValue(result.value as ArrayList<Species>)
                    Log.i("result.value", result.value.toString())
                }
                is BaseResult.NetworkError -> {
                    networkErrorLiveData.postValue(result.msgError)
                }
                is BaseResult.ServerError -> {
                    serverErrorLiveData.postValue(result.msgError)
                }
            }
        }
    }
}