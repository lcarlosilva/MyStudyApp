package com.luiz.mobile.mystudyapp.domain.repository

import com.luiz.mobile.mystudyapp.domain.result.BaseResult

interface RickMortyRepository {
    fun species(resultCallback: (result: BaseResult) -> Unit)
}
