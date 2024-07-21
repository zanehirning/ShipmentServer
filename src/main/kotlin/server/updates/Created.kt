package server.updates

import server.shipment.Shipment
import server.TrackingSimulator

class Created : Update {
    override fun apply(shipment: Shipment, otherInfo: String) {
        shipment.status = "created"
        TrackingSimulator.addShipment(shipment)
    }
}