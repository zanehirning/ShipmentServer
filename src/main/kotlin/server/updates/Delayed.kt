package server.updates

import server.shipment.Shipment

class Delayed : Update {
    override fun apply(shipment: Shipment, timeStampOfUpdate: Long, otherInfo: String) {
        shipment.status = "delayed"
        shipment.addUpdate(timeStampOfUpdate)
        shipment.expectedDeliveryDateTimestamp = otherInfo.toLong()
    }
}