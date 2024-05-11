package com.ougi.callme.domain.repository

import io.ktor.client.statement.*
import io.ktor.http.*

interface AuthRepository {

    suspend fun authenticateRequest(headers: Headers): HttpResponse

}