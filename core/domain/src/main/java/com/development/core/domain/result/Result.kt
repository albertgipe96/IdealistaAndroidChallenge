package com.development.core.domain.result

sealed interface Result<out D, out E: Error> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Error<out E: com.development.core.domain.result.Error>(val error: E) :
        Result<Nothing, E>
}

inline fun <T, E: Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when (this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

typealias EmptyDataResult<E> = Result<Unit, E>