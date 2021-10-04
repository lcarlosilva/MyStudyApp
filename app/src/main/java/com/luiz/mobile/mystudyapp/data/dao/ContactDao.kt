package com.luiz.mobile.mystudyapp.data.dao

import androidx.room.*
import com.luiz.mobile.mystudyapp.domain.entity.Contact

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(contact: Contact)

    @Delete
    fun remove(contact: Contact)

    @Update
    fun update(contact: Contact)

    @Query("SELECT * FROM tb_contact")
    fun getAll(): List<Contact>

    @Query("SELECT * FROM tb_contact WHERE id = :id")
    fun getById(id: Long): Contact
}