package server.updates

import server.controllers.TrackingController
import server.shipment.Shipment

class Created : Update {
    override fun apply(shipment: Shipment, otherInfo: String) {
        shipment.status = "created"
        TrackingController.addShipment(shipment)
    }
}