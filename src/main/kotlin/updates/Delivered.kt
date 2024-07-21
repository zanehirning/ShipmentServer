package updates

import shipment.Shipment

class Delivered : Update {
    override fun apply(shipment: Shipment, otherInfo: String) {
        shipment.status = "delivered"
    }
}