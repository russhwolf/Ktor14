package com.example

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.CoroutineScope
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

expect fun <T> runBlocking(context: CoroutineContext = EmptyCoroutineContext, block: suspend CoroutineScope.() -> T): T

class ApiClientTest {
    @Test
    fun mockWebCall() {
        runBlocking {
            val mockEngine = MockEngine {
                respond(
                    status = HttpStatusCode.OK,
                    content = ByteReadChannel("""{"ip":"127.0.0.1"}"""),
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            val apiClient = ApiClient(mockEngine)
            val response = apiClient.getIp()
            assertEquals("127.0.0.1", response.ip)
        }
    }
}
