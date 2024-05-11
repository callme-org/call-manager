package com.ougi.callme.presentation.routing

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(){
    routing {
        configureCallWebSocket()
    }
}