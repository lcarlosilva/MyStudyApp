package com.luiz.mobile.mystudyapp.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse<T> (
        @Expose @SerializedName("count") val count: Int,
        @Expose @SerializedName("next") val next: String,
        @Expose @SerializedName("previous") val previous: String,
        @Expose @SerializedName("results") val results: ArrayList<T>
)