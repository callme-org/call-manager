package com.ougi.callme.data.repository

import com.ougi.callme.domain.repository.AuthRepository
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class AuthRepositoryImpl(private val client: HttpClient) : AuthRepository {

    override suspend fun authenticateRequest(headers: Headers): HttpResponse =
        client.request("http://callme-auth:8080/open/auth/authenticate") {
            this.headers.appendAll(headers)
        }
}