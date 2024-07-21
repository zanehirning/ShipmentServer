package server.updates

import server.shipment.Shipment

class Delayed : Update {
    override fun apply(shipment: Shipment, otherInfo: String) {
        shipment.status = "delayed"
        shipment.expectedDeliveryDateTimestamp = otherInfo.toLong()
    }
}