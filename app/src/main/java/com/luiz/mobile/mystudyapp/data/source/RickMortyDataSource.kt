package com.luiz.mobile.mystudyapp.data.source

import android.util.Log
import com.luiz.mobile.mystudyapp.data.response.BaseResponse
import com.luiz.mobile.mystudyapp.data.service.RickMortyService
import com.luiz.mobile.mystudyapp.domain.entity.Character
import com.luiz.mobile.mystudyapp.domain.repository.RickMortyRepository
import com.luiz.mobile.mystudyapp.domain.result.BaseResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RickMortyDataSource(private val service: RickMortyService) : RickMortyRepository {
    override fun characters(resultCallback: (result: BaseResult) -> Unit) {
        val callAsync = service.characters()
        callAsync.enqueue(object : Callback<BaseResponse<Character>> {
            override fun onResponse(
                call: Call<BaseResponse<Character>>,
                response: Response<BaseResponse<Character>>
            ) {
                when {
                    response.isSuccessful -> {
                        response.body()?.let {
                            Log.i("SUCCESS response result - ", it.results.toString())
                            resultCallback.invoke(BaseResult.Success(it.results))
                        }
                    }
                    else -> {
                        Log.i("ERROR response errorBody - ", response.message())
                        resultCallback.invoke(
                            BaseResult.NetworkError(
                                response.errorBody().toString()
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse<Character>>, t: Throwable) {
                Log.i("onFailure - ", t.message.toString())
                resultCallback.invoke(BaseResult.ServerError(t.message.toString()))
            }
        })
    }
}