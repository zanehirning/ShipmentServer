package updates

import shipment.Shipment

class Shipped : Update {
    override fun apply(shipment: Shipment, otherInfo: String) {
        shipment.status = "shipped"
        shipment.expectedDeliveryDateTimestamp = otherInfo.toLong()
    }
}