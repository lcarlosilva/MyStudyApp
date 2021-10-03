package com.luiz.mobile.mystudyapp.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "contact_name")
    val name: String,
    @ColumnInfo(name = "contact_age")
    val age: Int,
    @ColumnInfo(name = "contact_city")
    val city: String,
    /*@ColumnInfo(name = "contact_link")
    val link: LinkStatus,*/
)