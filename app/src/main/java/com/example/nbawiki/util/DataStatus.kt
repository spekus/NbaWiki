package com.example.nbawiki.util

import retrofit2.HttpException
import java.net.SocketTimeoutException

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}


//
//sealed class DataStatus <T> {
//    class Success <T> (val data: T) : DataStatus<T>()
//    class Error<T> (val exception: Exception) : DataStatus<T>()
//    class Loading<T> : DataStatus<T>()
////}
//sealed class Result<T, U>
//
//data class Success<T, U>(val value: T): Result<T, U>()
//
//data class Error<T, U>(val value: U): Result<T, U>()

//sealed class Resource<T>(data: T? = null, message: String? = null) {
//    class Success(data: String) : Resource(data = data)
////    class Loading : Resource()
//    class Error(message: String) : Resource(message = message)
//}

//sealed class Resource<T>(data: T? = null, message: String? = null) {
//    class Success(data: String) : Resource(data)
//    class Loading : Resource()
//    class Error(message: String) : Resource(message = message)
//}

//
//data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
//    companion object {
//        fun <T> success(data: T?): Resource<T> {
//            return Resource(SUCCESS, data, null)
//        }
//
//        fun <T> error(msg: String, data: T?): Resource<T> {
//            return Resource(ERROR, data, msg)
//        }
//
//        fun <T> loading(data: T?): Resource<T> {
//            return Resource(LOADING, data, null)
//        }
//    }
//}
//
//open class ResponseHandler {
//    fun <T : Any> handleSuccess(data: T): Resource<T> {
//        return Resource.success(data)
//    }
//
//    fun <T : Any> handleException(e: Exception): Resource<T> {
//        return when (e) {
//            is HttpException -> Resource.error(getErrorMessage(e.code()), null)
//            else -> Resource.error(getErrorMessage(Int.MAX_VALUE), null)
//        }
//    }
//
//    private fun getErrorMessage(code: Int): String {
//        return when (code) {
//            401 -> "Unauthorised"
//            404 -> "Not found"
//            else -> "Something went wrong"
//        }
//    }
//}