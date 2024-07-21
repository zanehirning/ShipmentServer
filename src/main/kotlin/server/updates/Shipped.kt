package server.updates

import server.shipment.Shipment

class Shipped : Update {
    override fun apply(shipment: Shipment, otherInfo: String) {
        shipment.status = "shipped"
        shipment.expectedDeliveryDateTimestamp = otherInfo.toLong()
    }
}