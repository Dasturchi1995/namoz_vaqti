package com.example.namozvaqtlari.data.remoute

sealed class ResponseResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>(): ResponseResult<T>()
    class Success<T>(data: T? = null): ResponseResult<T>(data = data)
    class Error<T>(message: String? = null): ResponseResult<T>(message = message)
}