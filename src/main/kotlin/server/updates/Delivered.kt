package server.updates

import server.shipment.Shipment

class Delivered : Update {
    override fun apply(shipment: Shipment, timeStampOfUpdate: Long, otherInfo: String) {
        shipment.status = "delivered"
        shipment.addUpdate(timeStampOfUpdate)
    }
}