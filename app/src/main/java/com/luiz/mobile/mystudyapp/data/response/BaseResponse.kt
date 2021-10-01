package com.luiz.mobile.mystudyapp.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BaseResponse<T>(
    @Expose @SerializedName("info") val info: Info,
    @Expose @SerializedName("results") val results: ArrayList<T>
) : Serializable {
    data class Info(
        @Expose @SerializedName("count") val count: Int,
        @Expose @SerializedName("pages") val pages: Int,
        @Expose @SerializedName("next") val next: String,
        @Expose @SerializedName("previous") val prev: String,
    ) : Serializable
}