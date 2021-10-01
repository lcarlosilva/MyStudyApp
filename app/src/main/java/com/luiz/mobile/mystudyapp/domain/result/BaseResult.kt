package com.luiz.mobile.mystudyapp.domain.result

sealed class BaseResult {
    class Success(val value: Any?) : BaseResult()
    class ServerError(val msgError: String) : BaseResult()
    class NetworkError(val msgError: String) : BaseResult()
}
