package server.updates

import server.shipment.Shipment

class Location : Update {
    override fun apply(shipment: Shipment, timeStampOfUpdate: Long, otherInfo: String) {
        shipment.location = otherInfo
    }
}