package com.luiz.mobile.mystudyapp.view.viewmodel.contacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luiz.mobile.mystudyapp.domain.entity.Contact
import com.luiz.mobile.mystudyapp.domain.repository.ContactRepository
import com.luiz.mobile.mystudyapp.domain.result.BaseResult

class ContactViewModel(private val repository: ContactRepository) : ViewModel() {

    val successLiveData: MutableLiveData<Any?> = MutableLiveData()
    val databaseErrorLiveData: MutableLiveData<String> = MutableLiveData()

    fun save(contact: Contact) {
        repository.save(contact) { result ->
            when (result) {
                is BaseResult.Success -> {
                    successLiveData.postValue(result.value as Contact)
                }
                is BaseResult.DatabaseError -> {
                    databaseErrorLiveData.postValue(result.msgError)
                }
            }
        }
    }

    fun remove(contact: Contact) {
        repository.remove(contact) { result ->
            when (result) {
                is BaseResult.Success -> {
                    successLiveData.postValue(result.value)
                }
                is BaseResult.DatabaseError -> {
                    databaseErrorLiveData.postValue(result.msgError)
                }
            }
        }
    }

    fun update(contact: Contact) {
        repository.update(contact) { result ->
            when (result) {
                is BaseResult.Success -> {
                    successLiveData.postValue(result.value)
                }
                is BaseResult.DatabaseError -> {
                    databaseErrorLiveData.postValue(result.msgError)
                }
            }
        }
    }

    fun getAll() {
        repository.getAll { result ->
            when (result) {
                is BaseResult.Success -> {
                    successLiveData.postValue(result.value as List<Contact>)
                }
                is BaseResult.DatabaseError -> {
                    databaseErrorLiveData.postValue(result.msgError)
                }
            }
        }
    }

    fun getById(id: Long) {
        repository.getById(id) { result ->
            when (result) {
                is BaseResult.Success -> {
                    successLiveData.postValue(result.value as Contact)
                }
                is BaseResult.DatabaseError -> {
                    databaseErrorLiveData.postValue(result.msgError)
                }
            }
        }
    }

    fun buildContact(id: Long = 0L, name: String, age: String, city: String): Contact =
        Contact(id = id, name = name, age = age, city = city)
}