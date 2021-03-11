package ru.bpproject.camestore.data.extdb

import retrofit2.Response

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(error.message ?: "unknown error")
        }
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else
                    ApiSuccessResponse(body)
                } else {
                    val msg = response.errorBody()?.string()
                    var errorMsg = msg
                    if (msg.isNullOrEmpty()) {
                        errorMsg = response.message()
                    }
                    ApiErrorResponse(errorMsg ?: "unknown error")
            }
        }
    }
}

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()