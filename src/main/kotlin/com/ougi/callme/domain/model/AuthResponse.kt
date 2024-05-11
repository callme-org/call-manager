package com.ougi.callme.domain.model

sealed interface AuthResponse {

    data object Authenticated : AuthResponse

    class Failure(val body: ByteArray) : AuthResponse
}