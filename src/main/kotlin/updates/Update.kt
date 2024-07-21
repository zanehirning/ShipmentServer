package updates

import shipment.Shipment

interface Update {
    fun apply(shipment: Shipment, otherInfo: String)
}