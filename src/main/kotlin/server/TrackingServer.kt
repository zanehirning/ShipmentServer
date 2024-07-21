package server

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import server.controllers.TrackingController.updateShipment

object TrackingServer {
    val server: ApplicationEngine
    init {
        server = embeddedServer(Netty, port = 8080) {
            routing {
                get("/") {
                    call.respondText { "hello" }
                }
                updateShipment()
            }
        }.start(wait = true)
    }
}