package com.ougi.callme.presentation.routing

import com.ougi.callme.domain.model.AuthResponse
import com.ougi.callme.domain.usecase.AuthRequestUseCase
import com.ougi.callme.presentation.model.Connection
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import org.koin.ktor.ext.inject
import java.util.*

fun Routing.configureCallWebSocket() {
    val authRequestUseCase by inject<AuthRequestUseCase>()
    val connections = Collections.synchronizedSet<Connection?>(LinkedHashSet())

    webSocket("/call/{id}") {

        val authorizeResponse = authRequestUseCase.authenticateRequest(call.request.headers)
        if (authorizeResponse is AuthResponse.Failure) {
            close(CloseReason(CloseReason.Codes.CANNOT_ACCEPT, authorizeResponse.body.decodeToString()))
            return@webSocket
        }

        val login = call.request.headers["login"]
            ?: run {
                close(CloseReason(CloseReason.Codes.CANNOT_ACCEPT, "Login must be specified"))
                return@webSocket
            }
        val callId = call.parameters["id"]
            ?: run {
                close(CloseReason(CloseReason.Codes.CANNOT_ACCEPT, "Id must be specified"))
                return@webSocket
            }


        val currentConnection = Connection(login, callId, this)
        connections += currentConnection

        try {
            for (frame in incoming) {
                frame as? Frame.Text ?: continue
                connections
                    .filter { connection -> connection.callId == callId }
                    .filterNot { connection -> connection.login == login }
                    .forEach { connection -> connection.session.send(frame) }
            }
        } catch (e: Exception) {
            println(e.localizedMessage)
        } finally {
            connections -= currentConnection
        }
    }
}