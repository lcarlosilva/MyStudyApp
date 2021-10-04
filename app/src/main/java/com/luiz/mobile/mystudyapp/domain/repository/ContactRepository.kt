package com.luiz.mobile.mystudyapp.domain.repository

import com.luiz.mobile.mystudyapp.domain.entity.Contact
import com.luiz.mobile.mystudyapp.domain.result.BaseResult

interface ContactRepository {
    fun save(contact: Contact, resultCallback: (result: BaseResult) -> Unit)
    fun remove(contact: Contact, resultCallback: (result: BaseResult) -> Unit)
    fun update(contact: Contact, resultCallback: (result: BaseResult) -> Unit)
    fun getAll(resultCallback: (result: BaseResult) -> Unit)
    fun getById(id: Long, resultCallback: (result: BaseResult) -> Unit)
}