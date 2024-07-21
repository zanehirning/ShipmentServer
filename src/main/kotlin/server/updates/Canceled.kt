package server.updates

import server.shipment.Shipment

class Canceled : Update {
    override fun apply(shipment: Shipment, otherInfo: String) {
        shipment.status = "canceled"
    }
}