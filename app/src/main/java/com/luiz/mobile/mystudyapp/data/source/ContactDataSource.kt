package com.luiz.mobile.mystudyapp.data.source

import com.luiz.mobile.mystudyapp.data.dao.ContactDao
import com.luiz.mobile.mystudyapp.domain.entity.Contact
import com.luiz.mobile.mystudyapp.domain.repository.ContactRepository
import com.luiz.mobile.mystudyapp.domain.result.BaseResult
import java.util.concurrent.Executors

class ContactDataSource(private val dao: ContactDao) : ContactRepository {

    override fun save(contact: Contact, resultCallback: (result: BaseResult) -> Unit) {
        try {
            Executors.newSingleThreadExecutor().execute {
                dao.save(contact)
                resultCallback.invoke(BaseResult.Success(contact))
            }
        } catch (e: Exception) {
            resultCallback.invoke(BaseResult.DatabaseError(e.message.toString()))
        }
    }

    override fun remove(contact: Contact, resultCallback: (result: BaseResult) -> Unit) {
        try {
            Executors.newSingleThreadExecutor().execute {
                dao.remove(contact)
                resultCallback.invoke(BaseResult.Success("removed"))
            }
        } catch (e: Exception) {
            resultCallback.invoke(BaseResult.DatabaseError(e.message.toString()))
        }
    }

    override fun update(contact: Contact, resultCallback: (result: BaseResult) -> Unit) {
        try {
            Executors.newSingleThreadExecutor().execute {
                dao.update(contact)
                resultCallback.invoke(BaseResult.Success(true))
            }
        } catch (e: Exception) {
            resultCallback.invoke(BaseResult.DatabaseError(e.message.toString()))
        }
    }

    override fun getAll(resultCallback: (result: BaseResult) -> Unit) {
        try {
            Executors.newSingleThreadExecutor().execute {
                val contacts = dao.getAll()
                resultCallback.invoke(BaseResult.Success(contacts))
            }
        } catch (e: Exception) {
            resultCallback.invoke(BaseResult.DatabaseError(e.message.toString()))
        }
    }

    override fun getById(id: Long, resultCallback: (result: BaseResult) -> Unit) {
        try {
            Executors.newSingleThreadExecutor().execute {
                val contacts = dao.getById(id)
                resultCallback.invoke(BaseResult.Success(contacts))
            }
        } catch (e: Exception) {
            resultCallback.invoke(BaseResult.DatabaseError(e.message.toString()))
        }
    }
}