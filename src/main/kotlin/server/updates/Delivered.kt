package server.updates

import server.shipment.Shipment

class Delivered : Update {
    override fun apply(shipment: Shipment, otherInfo: String) {
        shipment.status = "delivered"
    }
}