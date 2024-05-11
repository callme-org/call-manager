package com.ougi.callme.presentation.model

import io.ktor.server.websocket.*

class Connection(
    val login: String,
    val callId: String,
    val session: WebSocketServerSession
)