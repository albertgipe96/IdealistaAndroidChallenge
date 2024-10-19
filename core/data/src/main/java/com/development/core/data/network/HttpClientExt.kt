package com.development.core.data.network

import com.development.core.domain.result.DataError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.SerializationException
import java.nio.channels.UnresolvedAddressException
import kotlin.coroutines.cancellation.CancellationException
import com.development.core.domain.result.Result

private const val BASE_URL = "https://idealista.github.io"

fun constructRoute(route: String): String {
    return when {
        route.contains(BASE_URL) -> route
        route.startsWith("/") -> "$BASE_URL$route"
        else -> "$BASE_URL/$route"
    }
}

suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, DataError.Network> {
    return when (response.status.value) {
        in 200..299 -> Result.Success(response.body<T>())
        401 -> Result.Error(com.development.core.domain.result.DataError.Network.UNAUTHORIZED)
        408 -> Result.Error(com.development.core.domain.result.DataError.Network.REQUEST_TIMEOUT)
        409 -> Result.Error(com.development.core.domain.result.DataError.Network.CONFLICT)
        413 -> Result.Error(com.development.core.domain.result.DataError.Network.PAYLOAD_TOO_LARGE)
        429 -> Result.Error(com.development.core.domain.result.DataError.Network.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(com.development.core.domain.result.DataError.Network.SERVER_ERROR)
        else -> Result.Error(com.development.core.domain.result.DataError.Network.UNKNOWN)
    }
}

suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): Result<T, DataError.Network> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        e.printStackTrace()
        return Result.Error(com.development.core.domain.result.DataError.Network.NO_INTERNET)
    } catch (e: SerializationException) {
        e.printStackTrace()
        return Result.Error(com.development.core.domain.result.DataError.Network.SERIALIZATION)
    } catch (e: Exception) {
        if (e is CancellationException) throw e // If coroutine has been cancelled, we need to propagate to its parents to don't break any behavior
        e.printStackTrace()
        return Result.Error(com.development.core.domain.result.DataError.Network.UNKNOWN)
    }
    return responseToResult(response)
}

suspend inline fun <reified Response: Any> HttpClient.get(
    route: String,
    queryParams: Map<String, Any?> = mapOf()
): Result<Response, DataError.Network> {
    return safeCall {
        get {
            url(constructRoute(route))
            queryParams.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}