package server

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import server.controllers.TrackingController.updateShipment
import java.io.File

object TrackingServer {
    val server: ApplicationEngine
    init {
        server = embeddedServer(Netty, port = 8080) {
            routing {
                get("/") {
                    call.respondText(File("src/main/kotlin/client/index.html").readText(), ContentType.Text.Html)
                }
                updateShipment()
            }
        }.start(wait = true)
    }
}