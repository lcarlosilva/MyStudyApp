package com.luiz.mobile.mystudyapp.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tb_contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = "contact_name")
    var name: String = "",
    @ColumnInfo(name = "contact_age")
    var age: String = "",
    @ColumnInfo(name = "contact_city")
    var city: String = ""
) : Serializable