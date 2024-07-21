package server.updates

import server.shipment.Shipment

class Location : Update {
    override fun apply(shipment: Shipment, otherInfo: String) {
        shipment.location = otherInfo
    }
}