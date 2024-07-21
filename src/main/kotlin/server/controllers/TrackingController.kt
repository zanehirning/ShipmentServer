package server.controllers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import server.enums.ShipmentType
import server.shipment.Dispatcher
import server.shipment.Shipment
import server.updates.*

private data class UpdateRequestData(val shipmentId: String, val operation: String, val timeStamp: String, val otherInfo: String?)

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
    private val dispatcher = Dispatcher()
    private var shipments: List<Shipment> = listOf()

    fun findShipment(id: String): Shipment? {
        return shipments.find { it.id == id }
    }

    fun addShipment(shipment: Shipment) {
        shipments += shipment
    }

    private fun readData(data: String): UpdateRequestData {
        val splitData = data.split(",").map { it.trim() }
        return UpdateRequestData(splitData[0], splitData[1], splitData[2], splitData.slice(3 until splitData.size).joinToString(","))
    }

    fun Route.updateShipment() {
        post("/update") {
            try {
                val updateData = readData(call.receiveText())
                val shipment = findShipment(updateData.shipmentId) ?: dispatcher.createShipment(ShipmentType.valueOf(updateData.otherInfo?.uppercase() ?: ""), updateData.shipmentId)
                if (updateData.operation in updateOperations) {
                    shipment.addUpdate(updateOperations[updateData.operation]!!, updateData.timeStamp, updateData.otherInfo ?: "")
                }
                call.respond(HttpStatusCode.OK, "Shipment Updated Successfully!")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Bad Request: ${e.message}")
            }
        }
    }
}