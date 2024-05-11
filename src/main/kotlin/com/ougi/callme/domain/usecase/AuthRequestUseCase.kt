package com.ougi.callme.domain.usecase

import com.ougi.callme.domain.model.AuthResponse
import com.ougi.callme.domain.repository.AuthRepository
import io.ktor.client.statement.*
import io.ktor.http.*

interface AuthRequestUseCase {

    suspend fun authenticateRequest(headers: Headers): AuthResponse
}

class AuthRequestUseCaseImpl(
    private val authRepository: AuthRepository
) : AuthRequestUseCase {

    override suspend fun authenticateRequest(headers: Headers): AuthResponse =
        authRepository.authenticateRequest(headers)
            .let { response ->
                if (response.status == HttpStatusCode.OK) AuthResponse.Authenticated
                else AuthResponse.Failure(response.readBytes())
            }

}