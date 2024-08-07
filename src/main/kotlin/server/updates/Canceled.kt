package server.updates

import server.shipment.Shipment

class Canceled : Update {
    override fun apply(shipment: Shipment, timeStampOfUpdate: Long, otherInfo: String) {
        shipment.status = "canceled"
        shipment.addUpdate(timeStampOfUpdate)
    }
}