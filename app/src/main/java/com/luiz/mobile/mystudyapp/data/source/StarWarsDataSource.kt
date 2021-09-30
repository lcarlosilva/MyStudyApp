package com.luiz.mobile.mystudyapp.data.source

import com.luiz.mobile.mystudyapp.data.response.BaseResponse
import com.luiz.mobile.mystudyapp.data.service.StarWarsService
import com.luiz.mobile.mystudyapp.domain.entity.Species
import com.luiz.mobile.mystudyapp.domain.repository.StarWarsRepository
import com.luiz.mobile.mystudyapp.domain.result.BaseResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StarWarsDataSource(private val service: StarWarsService): StarWarsRepository {
    override fun species(resultCallback: (result: BaseResult) -> Unit) {
        val callAsync = service.species()
        callAsync.enqueue(object : Callback<BaseResponse<Species>>{
            override fun onResponse(call: Call<BaseResponse<Species>>, response: Response<BaseResponse<Species>>) {
                when {
                    response.isSuccessful -> {
                        response.body()?.let {
                            resultCallback.invoke(BaseResult.Success(it.results))
                        }
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse<Species>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}