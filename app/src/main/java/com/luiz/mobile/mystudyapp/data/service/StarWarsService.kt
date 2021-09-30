package com.luiz.mobile.mystudyapp.data.service

import com.luiz.mobile.mystudyapp.data.response.BaseResponse
import com.luiz.mobile.mystudyapp.domain.entity.Species
import retrofit2.Call
import retrofit2.http.GET

interface StarWarsService {

    @GET("species")
    fun species() : Call<BaseResponse<Species>>

}
