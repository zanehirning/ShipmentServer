package server.controllers

import io.ktor.server.application.*
import io.ktor.server.routing.*
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
        post("/{shipmentId}/update") {
            val shipmentId = call.parameters["shipmentId"]
            // TODO: Get operation, time stamp, and other info

        }
    }
}