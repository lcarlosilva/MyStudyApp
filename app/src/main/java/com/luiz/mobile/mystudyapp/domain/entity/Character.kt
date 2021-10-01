package com.luiz.mobile.mystudyapp.domain.entity

import java.io.Serializable

data class Character(
        val id: Int,
        val name: String,
        val species: String,
        val gender: String,
        val location: Location
) : Serializable {
        data class Location(
                val name: String
        ) : Serializable
}
