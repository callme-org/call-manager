package com.ougi.callme.presentation

import com.ougi.callme.di.appModule
import com.ougi.callme.presentation.routing.configureRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.websocket.*
import org.koin.ktor.plugin.Koin
import java.time.Duration

fun main() {
    embeddedServer(
        factory = Netty,
        port = 8081,
        host = "0.0.0.0",
        module = Application::module
    )
        .start(wait = true)
}

fun Application.module() {
    installPlugins()
    configureRouting()
}

private fun Application.installPlugins() {
    install(Koin) {
        modules(appModule)
    }
    install(WebSockets) {
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
}
