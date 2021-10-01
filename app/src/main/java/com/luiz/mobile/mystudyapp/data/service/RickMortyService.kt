package com.luiz.mobile.mystudyapp.data.service

import com.luiz.mobile.mystudyapp.data.response.BaseResponse
import com.luiz.mobile.mystudyapp.domain.entity.Character
import retrofit2.Call
import retrofit2.http.GET

interface RickMortyService {
    @GET("character")
    fun characters(): Call<BaseResponse<Character>>
}
