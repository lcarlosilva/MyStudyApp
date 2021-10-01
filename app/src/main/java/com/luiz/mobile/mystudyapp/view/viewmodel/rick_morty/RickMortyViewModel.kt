package com.luiz.mobile.mystudyapp.view.viewmodel.rick_morty

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luiz.mobile.mystudyapp.domain.entity.Character
import com.luiz.mobile.mystudyapp.domain.repository.RickMortyRepository
import com.luiz.mobile.mystudyapp.domain.result.BaseResult

class RickMortyViewModel(private val repository: RickMortyRepository) : ViewModel() {

    val successLiveData: MutableLiveData<ArrayList<Character>> = MutableLiveData()
    val networkErrorLiveData: MutableLiveData<String> = MutableLiveData()
    val serverErrorLiveData: MutableLiveData<String> = MutableLiveData()

    fun species() {
        repository.species { result ->
            when (result) {
                is BaseResult.Success -> {
                    successLiveData.postValue(result.value as ArrayList<Character>)
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