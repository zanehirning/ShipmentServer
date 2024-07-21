package server.controllers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import server.TrackingSimulator
import server.shipment.Shipment
import server.updates.*

object TrackingController {
    private val updateOperations: Map<String, Update> = mapOf(
        "created" to Created(),
        "canceled" to Canceled(),
        "delayed" to Delayed(),
        "delivered" to Delivered(),
        "location" to Location(),
        "lost" to Lost(),
        "noteadded" to NoteAdded(),
        "shipped" to Shipped()
    )
    private var shipments: List<Shipment> = listOf()

    fun findShipment(id: String): Shipment? {
        return shipments.find { it.id == id }
    }

    fun addShipment(shipment: Shipment) {
        shipments += shipment
    }

    fun Route.updateShipment() {
        post("/update/{shipmentId}") {
            val shipmentId = call.parameters["shipmentId"].toString()
            // TODO: Get operation, time stamp, and other info
            println(shipmentId)
//            val shipment = findShipment(shipmentId) ?: Shipment(shipmentId)
//            if (operation in TrackingSimulator.updateOperations) {
//                shipment.addUpdate(TrackingSimulator.updateOperations[operation]!!, timeStampOfUpdate, otherInfo)
//            }
            call.respond(HttpStatusCode.OK, "Shipment Updated Successfully!")
        }
    }
}