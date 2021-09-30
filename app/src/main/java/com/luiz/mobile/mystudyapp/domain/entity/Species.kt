package com.luiz.mobile.mystudyapp.domain.entity

import java.io.Serializable

data class Species(
        val name: String,
        val classification: String,
        val designation: String,
        val language: String
) : Serializable
